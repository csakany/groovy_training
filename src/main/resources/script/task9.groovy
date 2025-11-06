import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    try {
        def request = message.getProperty("requestedRaise") ?: "0"
        def raisePercent = request as BigDecimal
        message.setBody("Requested raise: ${raisePercent}%")
        message.setProperty("raiseValid", true)
    } catch (Exception ex) {
        message.setBody("Invalid raise value provided.")
        message.setProperty("raiseValid", false)
        message.setProperty("errorMessage", ex.message)
    } finally {
        message.setProperty("checkedValue", message.getProperty("requestedRaise"))
    }
    return message
}
