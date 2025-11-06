// This demonstration parses a manager directory XML payload to extract name and division details.
// It ties directly to the XML parsing overview on slides 29-31 along with ongoing message handling from slide 47.
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
