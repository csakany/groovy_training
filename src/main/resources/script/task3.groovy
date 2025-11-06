// This demonstration performs payroll math with BigDecimal to show arithmetic operators and formatting for HR compensation reporting.
// It ties back to the operator explanations on slides 4-5 and the message handling reminders from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def baseSalary = (message.getProperty("baseSalary") ?: "0") as BigDecimal
    def bonus = (message.getProperty("bonus") ?: "0") as BigDecimal
    def taxRate = (message.getProperty("taxRate") ?: "0") as BigDecimal
    def totalComp = baseSalary + bonus
    def netComp = totalComp - (totalComp * taxRate / 100)
    def report = """Base Salary: ${baseSalary}\nBonus: ${bonus}\nTotal Compensation: ${totalComp}\nNet After Tax: ${netComp.setScale(2, BigDecimal.ROUND_HALF_UP)}"""
    message.setBody(report)
    message.setProperty("totalComp", totalComp)
    message.setProperty("netComp", netComp.setScale(2, BigDecimal.ROUND_HALF_UP))
    return message
}
