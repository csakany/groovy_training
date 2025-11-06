// This demonstration converts XML company data into CSV text to illustrate lightweight transformations.
// It applies the XML parsing and generation concepts from slides 29-35 alongside message manipulation from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper

def Message processData(Message message) {
    def xmlText = message.getBody(String)
    def parsed = new XmlSlurper().parseText(xmlText)
    def companies = parsed.FOCompany.FOCompany.take(3)
    def header = "Code,Name"
    def rows = companies.collect { company -> "${company.externalCode.text()},${company.name.text()}" }
    def csv = ([header] + rows).join("\n")
    message.setBody(csv)
    message.setProperty("csvRows", rows.size())
    return message
}
