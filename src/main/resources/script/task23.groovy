// This demonstration builds a fresh employee directory XML with MarkupBuilder from comma-separated HR data.
// It aligns with the XML generation guidance on slides 33-35 while applying the CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.MarkupBuilder

def Message processData(Message message) {
    def employeeText = message.getProperty("employeeList") ?: "Alex Kim|HR,Sam Lee|IT"
    // split/collect/trim prepares employee entries, and the inner split("|") pulls name/department pairs from each string.
    def employees = employeeText.split(",").collect { it.trim() }.findAll { it }
            .collect { entry ->
                def parts = entry.split("\\|")
                [name: parts[0], department: parts.length > 1 ? parts[1] : "General"]
            }

    // StringWriter captures the generated XML in memory and MarkupBuilder turns Groovy closures into tag structures.
    def writer = new StringWriter()
    def builder = new MarkupBuilder(writer)
    builder.Employees {
        // eachWithIndex supplies both the employee map and its index so we can assign sequential IDs.
        employees.eachWithIndex { info, idx ->
            Employee(id: idx + 1) {
                Name(info.name)
                Department(info.department)
            }
        }
    }

    def xmlOutput = writer.toString()
    // toString() unwraps the writer contents and setProperty notes how many employees were encoded.
    message.setBody(xmlOutput)
    message.setProperty("employeeCount", employees.size())
    return message
}

/*
Practice Task 23:
- Read employee entries like "Name|Department" from a property and use MarkupBuilder to generate an Employees XML document.
- Output the XML in the body and store the total number of employees as a property following the example.
*/
