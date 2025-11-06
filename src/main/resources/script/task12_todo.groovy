import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 12: Use a closure.\n""" +
            "1. Read a list of names.\n" +
            "2. Create a closure that receives one name and returns a greeting.\n" +
            "3. Use collect with the closure to build greeting lines.\n" +
            "4. Count how many greetings were created."
    message.setBody(instructions)
    message.setProperty("nextStep", "Closures can be stored in variables and reused.")
    return message
}
