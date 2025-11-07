// This demonstration blends Groovy's dynamic typing with string interpolation to welcome a new hire and inspect property types.
// It reinforces the fundamentals from slides 2-4 and the CPI message techniques on slide 47 in a single onboarding example.
import com.sap.gateway.ip.core.customdev.util.Message

import java.math.RoundingMode

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "New Hire"
    def department = message.getProperty("department") ?: "Company"
    Integer idValue = (message.getProperty("employeeId") ?: "0") as Integer
    BigDecimal salaryValue = (message.getProperty("salary") ?: "0") as BigDecimal
    Boolean activeValue = (message.getProperty("active") ?: "false").toBoolean()

    def summary = """Welcome ${name} to the ${department} team!\n""" +
            """Employee ID (${idValue.getClass().simpleName}): ${idValue}\n""" +
            """Salary (${salaryValue.getClass().simpleName}): ${salaryValue.setScale(2, RoundingMode.HALF_UP)}\n""" +
            """Active (${activeValue.getClass().simpleName}): ${activeValue}"""

    message.setBody(summary)
    message.setProperty("nameLength", name.length())
    message.setProperty("departmentUpper", department.toUpperCase())
    message.setProperty("idType", idValue.getClass().simpleName)
    message.setProperty("salaryType", salaryValue.getClass().simpleName)
    message.setProperty("activeType", activeValue.getClass().simpleName)
    return message
}
