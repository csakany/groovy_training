import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def idValue = (message.getProperty("employeeId") ?: "0") as Integer
    def salaryValue = (message.getProperty("salary") ?: "0") as BigDecimal
    def activeValue = (message.getProperty("active") ?: "false").toBoolean()
    def summary = """Employee ID: ${idValue}\nID Type: ${idValue.getClass().simpleName}\nSalary Type: ${salaryValue.getClass().simpleName}\nActive Type: ${activeValue.getClass().simpleName}"""
    message.setBody(summary)
    message.setProperty("idType", idValue.getClass().simpleName)
    message.setProperty("salaryType", salaryValue.getClass().simpleName)
    message.setProperty("activeType", activeValue.getClass().simpleName)
    return message
}
