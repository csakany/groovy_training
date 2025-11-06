// This demonstration pauses briefly before enriching an employee XML body with department details from a property-stored XML.
// It illustrates CPI timing controls and cross-document lookups from slides 53-57 while applying the message techniques on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.xml.XmlUtil

def Message processData(Message message) {
    def employeeXmlText = message.getBody() as String
    def directoryXmlText = message.getProperty("departmentDirectory") as String ?: '<Departments/>'

    def delayMillis = (message.getProperty("delayMillis") ?: "50") as Long
    sleep(delayMillis)

    def employeeDoc = new XmlSlurper(false, false).parseText(employeeXmlText)
    def directoryDoc = new XmlSlurper(false, false).parseText(directoryXmlText)
    def departmentCode = employeeDoc.@departmentCode?.text()
    def departmentName = directoryDoc.Department.find { it.@code.text() == departmentCode }?.Name?.text() ?: "Unknown"

    if (!employeeDoc.DepartmentName) {
        employeeDoc.appendNode { DepartmentName(departmentName) }
    } else {
        employeeDoc.DepartmentName.replaceBody(departmentName)
    }

    def enrichedXml = XmlUtil.serialize(employeeDoc)
    message.setBody(enrichedXml)
    message.setProperty("departmentName", departmentName)
    message.setProperty("delayAppliedMillis", delayMillis)
    return message
}
