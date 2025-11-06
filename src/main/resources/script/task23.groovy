// This demonstration builds a fresh employee directory XML with MarkupBuilder from comma-separated HR data.
// It aligns with the XML generation guidance on slides 33-35 while applying the CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {
    def employeeText = message.getProperty("employeeList") ?: "Alex Kim|HR,Sam Lee|IT"
    def employees = employeeText.split(",").collect { it.trim() }.findAll { it }
            .collect { entry ->
                def parts = entry.split("\\|")
                [name: parts[0], department: parts.length > 1 ? parts[1] : "General"]
            }

    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)
    builder.Employees {
        employees.eachWithIndex { info, idx ->
            Employee(id: idx + 1) {
                Name(info.name)
                Department(info.department)
            }
        }
    }

    def xmlOutput = writer.toString()
    message.setBody(xmlOutput)
    message.setProperty("employeeCount", employees.size())
    return message
}
