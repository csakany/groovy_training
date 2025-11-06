import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def xmlText = message.getBody(String)
    def parsed = new XmlSlurper().parseText(xmlText)
    def manager = parsed.User[0]
    def name = manager.displayName.text()
    def division = manager.division.text()
    def summary = "Manager: ${name}\nDivision: ${division}"
    message.setBody(summary)
    message.setProperty("managerName", name)
    message.setProperty("managerDivision", division)
    return message
}
