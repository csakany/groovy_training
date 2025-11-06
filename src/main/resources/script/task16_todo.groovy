import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 16: Use ranges.\n""" +
            "1. Create a numeric range such as 1..5.\n" +
            "2. Turn the range into a list of onboarding day messages.\n" +
            "3. Print the list as multiple lines.\n" +
            "4. Store the total number of days as a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Ranges can be iterated just like lists.")
    return message
}
