// This demonstration shows Task18PayrollEmployee with a method to apply raises for payroll adjustments.
// It references the method and encapsulation coverage on slides 24-25 alongside message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class Task18PayrollEmployee {
    String name
    BigDecimal salary

    void applyRaise(BigDecimal amount) {
        salary += amount
    }
}

def Message processData(Message message) {
    def employee = new Task18PayrollEmployee(
            name: message.getProperty("employeeName") ?: "Taylor",
            salary: (message.getProperty("salary") ?: 50000) as BigDecimal)
    employee.applyRaise(2500G)
    message.setBody("${employee.name} now earns $${employee.salary}")
    message.setProperty("raiseApplied", true)
    return message
}
