import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 15: Manage a map.\n""" +
            "1. Create a map of departments and their headcount.\n" +
            "2. Update one of the existing values.\n" +
            "3. Add a brand new department.\n" +
            "4. Print each entry on its own line."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use map.key = value to update entries.")
    return message
}
