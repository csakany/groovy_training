// This demonstration pauses briefly before enriching an employee XML body with department details from a property-stored XML.
// It illustrates CPI timing controls and cross-document lookups from slides 53-57 while applying the message techniques on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.xml.XmlUtil

def Message processData(Message message) {
    def employeeXmlText = message.getBody() as String
    def directoryXmlText = message.getProperty("departmentDirectory") as String ?: '<Departments/>'

    def delayMillis = (message.getProperty("delayMillis") ?: "50") as Long
    // sleep(delayMillis) pauses the script to simulate asynchronous waits when integrating systems.
    sleep(delayMillis)

    // XmlSlurper(false, false) skips validation and namespaces, producing a GPath-friendly XML structure.
    def employeeDoc = new XmlSlurper(false, false).parseText(employeeXmlText)
    def directoryDoc = new XmlSlurper(false, false).parseText(directoryXmlText)
    // The @departmentCode syntax reads an attribute, while find { ... } locates the matching department node.
    def departmentCode = employeeDoc.@departmentCode?.text()
    def departmentName = directoryDoc.Department.find { it.@code.text() == departmentCode }?.Name?.text() ?: "Unknown"

    if (!employeeDoc.DepartmentName) {
        // appendNode { ... } creates a new child element with the resolved department name.
        employeeDoc.appendNode { DepartmentName(departmentName) }
    } else {
        // replaceBody updates the existing DepartmentName element content when one is already present.
        employeeDoc.DepartmentName.replaceBody(departmentName)
    }

    // XmlUtil.serialize(employeeDoc) converts the modified GPathResult back into formatted XML text.
    def enrichedXml = XmlUtil.serialize(employeeDoc)
    message.setBody(enrichedXml)
    message.setProperty("departmentName", departmentName)
    message.setProperty("delayAppliedMillis", delayMillis)
    return message
}

/*
Practice Task 30:
- Sleep for a configurable delay, parse both the employee body and directory XML, and locate the matching department name.
- Add or update the department information in the employee XML, serialize the result, and surface helpful properties just like the enrichment above.
*/
