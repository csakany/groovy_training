import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def instructions = """Practice Task 24: Transform XML to CSV.\n""" +
            "1. Parse the XML document.\n" +
            "2. Select a few FOCompany entries.\n" +
            "3. Build a CSV string with a header row.\n" +
            "4. Set the CSV content as the body."
    message.setBody(instructions)
    message.setProperty("nextStep", "Remember to join the header and rows with new lines.")
    return message
}
