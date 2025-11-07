// This demonstration validates raise requests with try/catch/finally to keep HR workflows resilient.
// It directly reflects the exception handling practices from slides 10-11 alongside the message access focus from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    try {
        // getProperty retrieves the requested raise value, while the Elvis operator defaults to "0" when absent.
        def request = message.getProperty("requestedRaise") ?: "0"
        // as BigDecimal converts the text into a precise decimal number for financial calculations.
        def raisePercent = request as BigDecimal
        // setBody and setProperty record success details when the conversion succeeds.
        message.setBody("Requested raise: ${raisePercent}%")
        message.setProperty("raiseValid", true)
    } catch (Exception ex) {
        // catch blocks handle conversion failures by writing friendly error feedback and capturing ex.message.
        message.setBody("Invalid raise value provided.")
        message.setProperty("raiseValid", false)
        message.setProperty("errorMessage", ex.message)
    } finally {
        // finally always runs, so setProperty here guarantees the checked value is tracked regardless of outcome.
        message.setProperty("checkedValue", message.getProperty("requestedRaise"))
    }
    return message
}

/*
Practice Task 9:
1. Read the requestedRaise property and attempt to convert it to a number inside a try block.
2. Provide a helpful error message and flag when conversion fails in the catch block.
3. Use a finally block to record that the validation ran, mirroring the structure above.
*/
