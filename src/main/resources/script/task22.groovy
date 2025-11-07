// This demonstration compares XmlSlurper and XmlParser by reading the same manager XML with both APIs.
// It continues the XML exploration from slides 29-32 and keeps the CPI message workflow from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.util.XmlParser

def Message processData(Message message) {
    // getBody() fetches the XML payload for both parser implementations.
    def xmlText = message.getBody() as String
    // XmlSlurper produces a GPathResult for lazy navigation, while XmlParser builds a DOM-like Node tree.
    def slurped = new XmlSlurper().parseText(xmlText)
    def parsed = new XmlParser().parseText(xmlText)

    // GPath expressions locate the displayName nodes, and text() extracts the string value from each structure.
    def slurperName = slurped.User[0].displayName.text()
    def parserName = parsed.User[0].displayName[0].text()

    def summary = """XmlSlurper class: ${slurped.getClass().simpleName}\nXmlParser class: ${parsed.getClass().simpleName}\n""" +
            """Slurper display name: ${slurperName}\nParser display name: ${parserName}"""

    // getClass().simpleName highlights the runtime types so learners can contrast both APIs.
    message.setBody(summary)
    message.setProperty("slurperType", slurped.getClass().simpleName)
    message.setProperty("parserType", parsed.getClass().simpleName)
    return message
}

/*
Practice Task 22:
- Parse the same XML with XmlSlurper and XmlParser, extract a shared field, and compare the resulting Groovy types.
- Present the comparison in the body and store the type names as properties as shown above.
*/
