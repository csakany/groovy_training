// This demonstration collects reusable methods, including default parameters, to summarize HR role and compensation updates.
// It aligns with the method guidance on slides 12-13 while continuing the CPI message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

// buildSummary(name, role) uses Groovy's implicit return to send back the formatted string.
String buildSummary(String name, String role) {
    "${name} currently works as ${role}."
}

// The default argument percent = 5G lets callers skip a value and still apply a 5% raise.
BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
    salary + (salary * percent / 100)
}

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "Colleague"
    def role = message.getProperty("jobTitle") ?: "Team Member"
    BigDecimal salary = (message.getProperty("currentSalary") ?: "0") as BigDecimal
    // with { ... } runs a closure on the string value so we can convert it to BigDecimal only when it is present.
    BigDecimal percent = (message.getProperty("raisePercent") ?: "").with { it ? (it as BigDecimal) : null }

    def summary = buildSummary(name, role)
    // Calling applyRaise with and without the second argument shows how Groovy fills in the default percent.
    BigDecimal updatedSalary = percent ? applyRaise(salary, percent) : applyRaise(salary)
    // setScale(2, ...) ensures salary figures show two decimals using half-up rounding.
    def body = """${summary}\nSalary after raise: ${updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}"""

    // length() counts characters in the summary and setProperty shares the derived values downstream.
    message.setBody(body)
    message.setProperty("summaryLength", summary.length())
    message.setProperty("updatedSalary", updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
    return message
}
