import com.sap.gateway.ip.core.customdev.util.Message

String buildSummary(String name, String role) {
    // TODO: return a helpful summary sentence.
    return "Implement me"
}

def Message processData(Message message) {
    def instructions = """Practice Task 10: Write and call a method.\n""" +
            "1. Define a method that accepts a name and a job title.\n" +
            "2. Return a short sentence that combines both values.\n" +
            "3. Call the method inside processData.\n" +
            "4. Store the length of the final sentence as summaryLength."
    message.setBody(instructions)
    message.setProperty("nextStep", "Replace the placeholder return value in buildSummary.")
    return message
}
