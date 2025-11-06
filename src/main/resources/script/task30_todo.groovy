import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 30: Validate required data.\n""" +
            "1. Check that a property like employeeStatus exists.\n" +
            "2. Throw an exception with a clear message when it is missing.\n" +
            "3. When the value is present, write a short status note.\n" +
            "4. Store that the validation ran by setting a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Provide helpful error messages when throwing exceptions.")
    return message
}
