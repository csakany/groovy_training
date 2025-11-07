// This demonstration removes inactive employee nodes from XML using XmlParser and XmlUtil serialization.
// It follows the XML transformation guidance on slides 33-35 together with the CPI message handling reminders from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlParser
import groovy.xml.XmlUtil

def Message processData(Message message) {
    def xmlText = message.getBody() as String
    def parser = new XmlParser(false, false)
    def root = parser.parseText(xmlText)

    def removed = root.Employee.findAll { it.@status != 'active' }
    removed.each { root.remove(it) }

    def cleanedXml = XmlUtil.serialize(root)
    message.setBody(cleanedXml)
    message.setProperty("removedCount", removed.size())
    return message
}
