import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

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
