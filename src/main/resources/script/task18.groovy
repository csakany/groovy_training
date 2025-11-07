// This demonstration shows Task18PayrollEmployee applying raises and validating payroll IDs with regex checks.
// It combines the class design guidance from slides 24-25 with the regex validation tips from slides 44-45 plus message handling on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class Task18PayrollEmployee {
    String name
    BigDecimal salary
    String payrollId

    // applyRaise increases the salary field by adding the provided amount with += shorthand.
    void applyRaise(BigDecimal amount) {
        salary += amount
    }

    // hasValidPayrollId uses the Groovy regex operator (==~) to ensure the ID matches three letters followed by four digits.
    boolean hasValidPayrollId() {
        payrollId ==~ /[A-Z]{3}\d{4}/
    }
}

def Message processData(Message message) {
    // The map constructor populates the employee fields from message properties with sensible fallbacks.
    def employee = new Task18PayrollEmployee(
            name: message.getProperty("employeeName") ?: "Taylor",
            salary: (message.getProperty("salary") ?: 50000) as BigDecimal,
            payrollId: message.getProperty("payrollId") ?: "HRM1234")

    boolean validId = employee.hasValidPayrollId()
    if (validId) {
        // applyRaise(2500G) adds a 2,500 raise only when the payroll ID passes validation.
        employee.applyRaise(2500G)
    }

    def summary = "${employee.name} now earns ${employee.salary} (Payroll ID valid: ${validId})"
    // setBody reports the outcome and setProperty exposes whether the raise logic ran.
    message.setBody(summary)
    message.setProperty("raiseApplied", validId)
    message.setProperty("payrollIdValid", validId)
    return message
}

/*
Practice Task 18:
- Create a payroll employee class with salary and payrollId fields, plus helpers to apply raises and validate the ID with the same regex.
- Only adjust the salary when the ID matches the expected format and record both outcomes as properties.
*/
