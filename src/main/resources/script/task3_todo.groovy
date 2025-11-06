import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 3: Calculate employee pay.\n""" +
            "1. Read the base salary, bonus, and tax rate from properties.\n" +
            "2. Add salary and bonus together.\n" +
            "3. Subtract tax from the total to show the net amount.\n" +
            "4. Present all results clearly in the body and store totals as properties."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use arithmetic operators to fill in the calculations.")
    return message
}
