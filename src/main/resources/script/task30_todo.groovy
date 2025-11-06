import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.xml.XmlUtil

def Message processData(Message message) {
    /*
    Practice Task 30: Delay and enrich XML from another XML source.
    - Sleep for a configurable number of milliseconds (read from a property) to simulate CPI delay.
    - Parse the body XML plus a directory XML stored in a property and find the matching department name.
    - Add or update the department name inside the employee XML, then serialize it back to the body with helpful properties.
    Next step: Replace the TODO with your own enrichment logic.
    */
    // TODO: Implement the practice steps above.
    return message
}
