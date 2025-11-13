import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.util.XmlParser
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil

def Message processData(Message message) {
    // ------------------------------------------------------------
    // Input: Body holds the CE_response.xml content as String
    // ------------------------------------------------------------


    // ------------------------------------------------------------
    // PART A — XmlSlurper: read & reshape into a compact directory
    // ------------------------------------------------------------



    // iterate CompoundEmployee nodes and pick "current" job_information (end_date = 9999-12-31 if present)
    

    // Build a NEW XML structure from extracted data
    

    // ------------------------------------------------------------
    // PART B — XmlParser: in-place mutation on original tree
    // ------------------------------------------------------------
    // Example edits:
    //  1) Ensure all email domains are lowercased
    //  2) Prefix all business phone numbers with '+44-' (demo)
    //  3) Add a <extracted>true</extracted> flag under each person
    


    // email_information
    


    // phone_information


    // person



    // Serialize the mutated original


    // ------------------------------------------------------------
    // FINAL — Compose an output body that shows both results
    // ------------------------------------------------------------

    

    return message
}
