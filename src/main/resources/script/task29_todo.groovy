import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 29: Read headers and properties.\n""" +
            "1. Fetch a header such as EmployeeId.\n" +
            "2. Check if the header exists before using it.\n" +
            "3. Build a short summary that includes the body text.\n" +
            "4. Store whether the header was present as a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Remember that headers and properties can both be null.")
    return message
}
