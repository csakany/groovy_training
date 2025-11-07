// This demonstration parses manager XML with XmlSlurper to extract identity and contact fields from a CPI message body.
// It reflects the XML parsing walkthrough on slides 29-31 while continuing the message techniques from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper

def Message processData(Message message) {
    // getBody() retrieves the XML payload, and as String ensures XmlSlurper receives textual input.
    def xmlText = message.getBody() as String
    // new XmlSlurper().parseText(xmlText) creates a lazily parsed XML tree we can navigate with dot notation.
    def parsed = new XmlSlurper().parseText(xmlText)
    def manager = parsed.User[0]

    // Calling .text() on each node extracts the element content for display.
    def name = manager.displayName.text()
    def division = manager.division.text()
    def email = manager.email.text()
    def phone = manager.businessPhone.text()

    def summary = """Manager: ${name}\nDivision: ${division}\nEmail: ${email}\nPhone: ${phone}"""
    // setBody publishes the readable contact card, while setProperty exposes the individual fields downstream.
    message.setBody(summary)
    message.setProperty("managerName", name)
    message.setProperty("managerDivision", division)
    message.setProperty("contactEmail", email)
    message.setProperty("contactPhone", phone)
    return message
}
