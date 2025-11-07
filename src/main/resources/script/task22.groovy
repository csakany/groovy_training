// This demonstration compares XmlSlurper and XmlParser by reading the same manager XML with both APIs.
// It continues the XML exploration from slides 29-32 and keeps the CPI message workflow from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.util.XmlParser

def Message processData(Message message) {
    def xmlText = message.getBody() as String
    def slurped = new XmlSlurper().parseText(xmlText)
    def parsed = new XmlParser().parseText(xmlText)

    def slurperName = slurped.User[0].displayName.text()
    def parserName = parsed.User[0].displayName[0].text()

    def summary = """XmlSlurper class: ${slurped.getClass().simpleName}\nXmlParser class: ${parsed.getClass().simpleName}\n""" +
            """Slurper display name: ${slurperName}\nParser display name: ${parserName}"""

    message.setBody(summary)
    message.setProperty("slurperType", slurped.getClass().simpleName)
    message.setProperty("parserType", parsed.getClass().simpleName)
    return message
}
