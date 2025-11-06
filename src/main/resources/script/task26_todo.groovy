import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def instructions = """Practice Task 26: Modify JSON content.\n""" +
            "1. Parse the employeeJson property.\n" +
            "2. Update one of the values (for example salary).\n" +
            "3. Convert the structure back to JSON text.\n" +
            "4. Set the pretty printed JSON as the body."
    message.setBody(instructions)
    message.setProperty("nextStep", "JsonOutput.toJson turns Groovy maps back into JSON.")
    return message
}
