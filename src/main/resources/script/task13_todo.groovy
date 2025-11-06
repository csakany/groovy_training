import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 13: Filter with a closure.\n""" +
            "1. Create a small list of maps containing name and dept.\n" +
            "2. Read targetDept from the message.\n" +
            "3. Use findAll with a closure to keep matching colleagues.\n" +
            "4. Print the matches or a friendly \"not found\" message."
    message.setBody(instructions)
    message.setProperty("nextStep", "Closures can check each map and return true or false.")
    return message
}
