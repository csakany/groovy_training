import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

def Message processData(Message message) {
    def xmlText = message.getBody(String)
    def parsed = new XmlSlurper().parseText(xmlText)
    def companies = parsed.FOCompany.FOCompany
    def firstFive = companies.take(5).collect { company ->
        "${company.externalCode.text()} - ${company.name.text()}"
    }
    def summary = firstFive.join("\n")
    message.setBody(summary)
    message.setProperty("listedCompanies", firstFive.size())
    return message
}
