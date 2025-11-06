// This demonstration adds behavior to a PayrollEmployee class to calculate raises with encapsulated logic.
// It showcases the class and method design topics from slides 23-25 and maintains message access patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class PayrollEmployee {
    String name
    BigDecimal salary
    BigDecimal applyRaise(BigDecimal percent) {
        salary += salary * percent / 100
        return salary
    }
}

def Message processData(Message message) {
    def employee = new PayrollEmployee(
            name: message.getProperty("employeeName") ?: "Colleague",
            salary: (message.getProperty("currentSalary") ?: "0") as BigDecimal)
    def percent = (message.getProperty("raisePercent") ?: "0") as BigDecimal
    def updatedSalary = employee.applyRaise(percent)
    def summary = "${employee.name}'s salary is now ${updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}"
    message.setBody(summary)
    message.setProperty("updatedSalary", updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
    return message
}
