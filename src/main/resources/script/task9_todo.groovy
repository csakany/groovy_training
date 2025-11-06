import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 9: Catch bad input.\n""" +
            "1. Read the requestedRaise property.\n" +
            "2. Try to convert it to a number.\n" +
            "3. If conversion fails, show a friendly error message.\n" +
            "4. Use a finally block to record that the check was performed."
    message.setBody(instructions)
    message.setProperty("nextStep", "Wrap the conversion in a try/catch block.")
    return message
}
