// This demonstration blends Groovy's dynamic typing with string interpolation to welcome a new hire and inspect property types.
// It reinforces the fundamentals from slides 2-4 and the CPI message techniques on slide 47 in a single onboarding example.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonOutput

def Message processData(Message message) {
    // getProperty("employeeName") pulls a value the integration flow stored on the message.
    // The Elvis operator (?:) immediately after it supplies "New Hire" if the property is absent.
    def name = message.getProperty("employeeName") ?: "New Hire"
    def department = message.getProperty("department") ?: "Company"
    // (value as Integer) coerces the string payload into an Integer instance we can use in math.
    Integer idValue = (message.getProperty("employeeId") ?: "0") as Integer
    BigDecimal salaryValue = (message.getProperty("salary") ?: "0") as BigDecimal
    // toBoolean() converts text like "true" ("1") or "false" ("0") into a real Boolean flag.
    Boolean activeValue = (message.getProperty("active") ?: "false").toBoolean()

    def summary = "Welcome ${name} to the ${department} team!\n" +
            // getClass().simpleName reveals the runtime type so we can show the conversion result.
            "Employee ID (${idValue.getClass().simpleName}): ${idValue}\n" +
            "Salary (${salaryValue.getClass().simpleName}): ${salaryValue}\n" +
            "Active (${activeValue.getClass().simpleName}): ${activeValue}"

    def initials = name.split(' ').findAll { it }.collect { it[0] }.join('').toUpperCase()

    // setBody(summary) replaces the payload with our formatted welcome text.
    message.setBody(summary)
    // setProperty stores handy derived values on the message so later steps can reuse them.
    message.setProperty("nameLength", name.length())
    // length() counts characters in the name string, while toUpperCase() capitalizes the department text.
    message.setProperty("departmentUpper", department.toUpperCase())
    message.setProperty("idType", idValue.getClass().simpleName)
    message.setProperty("salaryType", salaryValue.getClass().simpleName)
    message.setProperty("activeType", activeValue.getClass().simpleName)
    message.setProperty("name", "${name}")
    message.setProperty("employeeInitials", initials)
    return message
}

/*
Practice Task 1:
- Read the employee name, department, ID, salary, and active status from message properties.
- Build a short summary that uses string interpolation and shows the Groovy types you converted to.
- Store helpful derived values such as uppercase department, name length, or detected type names.
- Focus on the same helpers shown above (property access, casting, formatting).
*/
