import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 4: Make a promotion decision.\n""" +
            "1. Fetch yearsOfService and performanceRating from message properties.\n" +
            "2. Use an if/else statement to decide if the employee qualifies.\n" +
            "3. Write a short explanation into the message body.\n" +
            "4. Store the decision as a property called promotionEligible."
    message.setBody(instructions)
    message.setProperty("nextStep", "Combine comparison and logical operators to reach a decision.")
    return message
}
