import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 1: Create a warm welcome message.\n""" +
            "1. Read the employee name from the message properties.\n" +
            "2. Read the department name from the message properties.\n" +
            "3. Build a sentence like \"Welcome Ana to the HR team!\" and set it as the body.\n" +
            "4. Store any helpful extra detail as a property (for example the name length)."
    message.setBody(instructions)
    message.setProperty("nextStep", "Follow the steps in the body to complete the practice task.")
    return message
}
