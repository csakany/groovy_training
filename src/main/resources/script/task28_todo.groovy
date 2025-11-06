import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 28: Validate with regular expressions.\n""" +
            "1. Read the email and phone properties.\n" +
            "2. Create regex checks for each value.\n" +
            "3. Print whether each value is valid.\n" +
            "4. Store the boolean results as properties."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use ==~ to check if the entire string matches the pattern.")
    return message
}
