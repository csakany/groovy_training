// This demonstration pulls HR contact information from XML to practice navigating nested elements.
// It reinforces the XmlSlurper techniques from slides 29-31 and message property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper

def Message processData(Message message) {
    def xmlText = message.getBody(String)
    def parsed = new XmlSlurper().parseText(xmlText)
    def manager = parsed.User[0]
    def email = manager.email.text()
    def phone = manager.businessPhone.text()
    def summary = "Contact Email: ${email}\nPhone: ${phone}"
    message.setBody(summary)
    message.setProperty("contactEmail", email)
    message.setProperty("contactPhone", phone)
    return message
}
