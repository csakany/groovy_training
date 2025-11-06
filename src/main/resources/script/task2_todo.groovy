import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 2: Explore Groovy data types.\n""" +
            "1. Fetch the employeeId, salary, and active status from properties.\n" +
            "2. Convert them to useful types (Integer, BigDecimal, Boolean).\n" +
            "3. Print the converted values and their types to the message body.\n" +
            "4. Save the type names as properties for later steps."
    message.setBody(instructions)
    message.setProperty("nextStep", "Convert the values and record their types.")
    return message
}
