import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 7: Build a while loop.\n""" +
            "1. Read the moduleList property for upcoming training modules.\n" +
            "2. Turn the text into a list.\n" +
            "3. Use a while loop to create numbered steps.\n" +
            "4. Output the steps and count how many modules exist."
    message.setBody(instructions)
    message.setProperty("nextStep", "Remember to update the loop counter inside the while loop.")
    return message
}
