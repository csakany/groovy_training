import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.xml.StreamingMarkupBuilder

def Message processData(Message message) {

    // ------------------------------------------------------------
    // STEP 1️⃣  Read message body (CE_response.xml content)
    // ------------------------------------------------------------
    String xmlText = message.getBody()
    if (!xmlText?.trim()) {
        throw new IllegalArgumentException("Message body must contain CE_response.xml as String.")
    }

    // ------------------------------------------------------------
    // STEP 2️⃣  Read property 'person_id_external' and convert to Set
    // Example: "1000123, 1000456,1000789"
    // ------------------------------------------------------------
    def idSet = (message.getProperty('person_id_external') ?: '')
            .split(',')   // split by comma, trim spaces
            .findAll()          // remove empties
            .toSet()

    // If property empty → return the original XML unchanged
    if (idSet.isEmpty()) {
        message.setProperty("filteredCount", 0)
        return message
    }

    // ------------------------------------------------------------
    // STEP 3️⃣  Parse the XML and filter CompoundEmployee nodes
    // ------------------------------------------------------------
    def slurper = new XmlSlurper(false, false)
    def root = slurper.parseText(xmlText)

    def matchingCEs = root.'**'.findAll { it.name() == 'CompoundEmployee' &&
            idSet.contains(it.person.person_id_external.text()) }

    // ------------------------------------------------------------
    // STEP 4️⃣  Build filtered XML with only matching employees
    // ------------------------------------------------------------
    def builder = new StreamingMarkupBuilder()
    def filteredXml = builder.bind {
        mkp.xmlDeclaration()
        FilteredCompoundEmployee(count: matchingCEs.size().toString()) {
            matchingCEs.each { ce -> mkp.yield ce }
        }
    }.toString()

    // ------------------------------------------------------------
    // STEP 5️⃣  Set message body and properties
    // ------------------------------------------------------------
    message.setBody(filteredXml)
    message.setProperty("filteredCount", matchingCEs.size())
    message.setProperty("requestedIds", idSet.join(","))
    return message
}
