import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper

def Message processData(Message message) {
    def instructions = """Practice Task 25: Parse JSON.\n""" +
            "1. Read the employeeJson property.\n" +
            "2. Parse it with JsonSlurper.\n" +
            "3. Access a couple of fields such as name and skills.\n" +
            "4. Display the information in the body."
    message.setBody(instructions)
    message.setProperty("nextStep", "JsonSlurper returns maps and lists that feel like Groovy objects.")
    return message
}
