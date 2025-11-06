import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

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
