import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 8: Use collection helpers.\n""" +
            "1. Read daily hour values from hourLogs.\n" +
            "2. Convert them into integers using collect.\n" +
            "3. Calculate the total and the average.\n" +
            "4. Show the numbers in the body and store totals as properties."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use split and collect to prepare the list before calling sum().")
    return message
}
