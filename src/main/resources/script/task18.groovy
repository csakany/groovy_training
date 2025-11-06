// This demonstration shows Task18PayrollEmployee applying raises and validating payroll IDs with regex checks.
// It combines the class design guidance from slides 24-25 with the regex validation tips from slides 44-45 plus message handling on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class Task18PayrollEmployee {
    String name
    BigDecimal salary
    String payrollId

    void applyRaise(BigDecimal amount) {
        salary += amount
    }

    boolean hasValidPayrollId() {
        payrollId ==~ /[A-Z]{3}\d{4}/
    }
}

def Message processData(Message message) {
    def employee = new Task18PayrollEmployee(
            name: message.getProperty("employeeName") ?: "Taylor",
            salary: (message.getProperty("salary") ?: 50000) as BigDecimal,
            payrollId: message.getProperty("payrollId") ?: "HRM1234")

    boolean validId = employee.hasValidPayrollId()
    if (validId) {
        employee.applyRaise(2500G)
    }

    def summary = "${employee.name} now earns $${employee.salary} (Payroll ID valid: ${validId})"
    message.setBody(summary)
    message.setProperty("raiseApplied", validId)
    message.setProperty("payrollIdValid", validId)
    return message
}
