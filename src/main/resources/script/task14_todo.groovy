import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 14: Work with lists.\n""" +
            "1. Start with a list of onboarding courses.\n" +
            "2. Add a new course to the end.\n" +
            "3. Remove a course that is not needed.\n" +
            "4. Show the updated plan and the total count."
    message.setBody(instructions)
    message.setProperty("nextStep", "Remember that << adds items and remove() deletes them.")
    return message
}
