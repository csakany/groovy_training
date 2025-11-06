import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 19: Extend a class.\n""" +
            "1. Create a base class with an introduce method.\n" +
            "2. Extend it with a TeamLead that adds more detail.\n" +
            "3. Use super to keep the original message.\n" +
            "4. Print the full introduction."
    message.setBody(instructions)
    message.setProperty("nextStep", "Override introduce() in the TeamLead subclass.")
    return message
}
