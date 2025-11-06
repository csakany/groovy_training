// This demonstration performs multi-level sorting on HR employee data using Groovy's custom comparator support.
// It extends the collection ordering tips from slides 42-43 while following the CPI message usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def employeesText = message.getProperty("employees") ?: "Alex Kim|HR|5, Sam Lee|IT|3, Priya Patel|HR|2"
    def employees = employeesText.split(",").collect { it.trim() }.findAll { it }
            .collect { entry ->
                def parts = entry.split("\\|")
                [name: parts[0], department: parts[1], tenure: parts[2] as Integer]
            }

    def sorted = employees.sort { a, b ->
        int deptCompare = a.department <=> b.department
        if (deptCompare != 0) {
            return deptCompare
        }
        int tenureCompare = b.tenure <=> a.tenure
        if (tenureCompare != 0) {
            return tenureCompare
        }
        return a.name <=> b.name
    }

    def lines = sorted.collect { "${it.department}: ${it.name} (${it.tenure} yrs)" }
    message.setBody(lines.join("\n"))
    message.setProperty("sortedCount", sorted.size())
    message.setProperty("topEmployee", sorted ? sorted.first().name : "")
    return message
}
