import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def instructions = """Practice Task 21: Parse XML basics.\n""" +
            "1. Read the XML body using XmlSlurper.\n" +
            "2. Navigate to the first User element.\n" +
            "3. Pull out the displayName and division values.\n" +
            "4. Show the values in the body and store them as properties."
    message.setBody(instructions)
    message.setProperty("nextStep", "Use element.text() to read XML values.")
    return message
}
