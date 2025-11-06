// This demonstration collects reusable methods, including default parameters, to summarize HR role and compensation updates.
// It aligns with the method guidance on slides 12-13 while continuing the CPI message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

String buildSummary(String name, String role) {
    "${name} currently works as ${role}."
}

BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
    salary + (salary * percent / 100)
}

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "Colleague"
    def role = message.getProperty("jobTitle") ?: "Team Member"
    BigDecimal salary = (message.getProperty("currentSalary") ?: "0") as BigDecimal
    BigDecimal percent = (message.getProperty("raisePercent") ?: "").with { it ? (it as BigDecimal) : null }

    def summary = buildSummary(name, role)
    BigDecimal updatedSalary = percent ? applyRaise(salary, percent) : applyRaise(salary)
    def body = """${summary}\nSalary after raise: ${updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}"""

    message.setBody(body)
    message.setProperty("summaryLength", summary.length())
    message.setProperty("updatedSalary", updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
    return message
}
