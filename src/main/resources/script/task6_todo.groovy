import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 6: Loop through a team list.\n""" +
            "1. Read a comma separated list of names from teamMembers.\n" +
            "2. Split the text into individual names.\n" +
            "3. Use a for loop to print each name on its own line.\n" +
            "4. Count how many names appear and store the number as memberCount."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use a for loop to walk through each team member.")
    return message
}
