import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 5: Use a switch statement.\n""" +
            "1. Read the contractType property (examples: FullTime, PartTime, Contractor).\n" +
            "2. With a switch statement, create a helpful explanation for each case.\n" +
            "3. Write the explanation to the body.\n" +
            "4. Save the explanation as a property called contractNote."
    message.setBody(instructions)
    message.setProperty("nextStep", "Map each contract type to a short description.")
    return message
}
