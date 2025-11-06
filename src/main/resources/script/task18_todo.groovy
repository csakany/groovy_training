import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 18: Add behavior to a class.\n""" +
            "1. Create a class with a salary field.\n" +
            "2. Implement a method that increases the salary.\n" +
            "3. Call the method and show the new salary.\n" +
            "4. Save the updated value in a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Update the salary field before returning from applyRaise.")
    return message
}
