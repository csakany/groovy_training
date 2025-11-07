// This demonstration removes inactive employee nodes from XML using XmlParser and XmlUtil serialization.
// It follows the XML transformation guidance on slides 33-35 together with the CPI message handling reminders from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlParser
import groovy.xml.XmlUtil

def Message processData(Message message) {
    def xmlText = message.getBody() as String
    // new XmlParser(false, false) creates a parser that ignores validation and namespace processing for simplicity.
    def parser = new XmlParser(false, false)
    def root = parser.parseText(xmlText)

    // root.Employee.findAll walks the child nodes and the @status syntax reads the status attribute on each element.
    def removed = root.Employee.findAll { it.@status != 'active' }
    // each { root.remove(it) } removes every inactive employee node from the tree.
    removed.each { root.remove(it) }

    // XmlUtil.serialize(root) converts the modified Node structure back into formatted XML text.
    def cleanedXml = XmlUtil.serialize(root)
    message.setBody(cleanedXml)
    message.setProperty("removedCount", removed.size())
    return message
}
