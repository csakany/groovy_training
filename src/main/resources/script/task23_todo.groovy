import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def instructions = """Practice Task 23: Loop through XML nodes.\n""" +
            "1. Parse the DM_Training.xml content from the body.\n" +
            "2. Collect the first few FOCompany entries.\n" +
            "3. Build lines that show the code and the name.\n" +
            "4. Write the lines to the body."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use take(5) to keep the list short.")
    return message
}
