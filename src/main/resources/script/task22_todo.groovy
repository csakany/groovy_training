import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def instructions = """Practice Task 22: Read more XML values.\n""" +
            "1. Reuse XmlSlurper to parse the message body.\n" +
            "2. Find the email and businessPhone elements.\n" +
            "3. Write the contact information to the body.\n" +
            "4. Store both values as properties for later steps."
    message.setBody(instructions)
    message.setProperty("nextStep", "The same parsed XML can deliver many fields.")
    return message
}
