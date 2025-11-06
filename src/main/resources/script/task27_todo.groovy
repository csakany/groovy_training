import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def instructions = """Practice Task 27: Sort collections.\n""" +
            "1. Read a comma separated list of names.\n" +
            "2. Turn it into a list.\n" +
            "3. Use the sort() method to order the names alphabetically.\n" +
            "4. Print the sorted result."
    message.setBody(instructions)
    message.setProperty("nextStep", "You can call sort() directly on the list.")
    return message
}
