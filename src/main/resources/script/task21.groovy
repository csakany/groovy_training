// This demonstration parses manager XML with XmlSlurper to extract identity and contact fields from a CPI message body.
// It reflects the XML parsing walkthrough on slides 29-31 while continuing the message techniques from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper

def Message processData(Message message) {
    def xmlText = message.getBody() as String
    def parsed = new XmlSlurper().parseText(xmlText)
    def manager = parsed.User[0]

    def name = manager.displayName.text()
    def division = manager.division.text()
    def email = manager.email.text()
    def phone = manager.businessPhone.text()

    def summary = """Manager: ${name}\nDivision: ${division}\nEmail: ${email}\nPhone: ${phone}"""
    message.setBody(summary)
    message.setProperty("managerName", name)
    message.setProperty("managerDivision", division)
    message.setProperty("contactEmail", email)
    message.setProperty("contactPhone", phone)
    return message
}
