import com.sap.gateway.ip.core.customdev.util.Message

BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
    return salary + (salary * percent / 100)
}

def Message processData(Message message) {
    def salary = (message.getProperty("currentSalary") ?: "0") as BigDecimal
    def percentText = message.getProperty("raisePercent")
    BigDecimal newSalary = percentText ? applyRaise(salary, (percentText as BigDecimal)) : applyRaise(salary)
    message.setBody("New salary after raise: ${newSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}")
    message.setProperty("updatedSalary", newSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
    return message
}
