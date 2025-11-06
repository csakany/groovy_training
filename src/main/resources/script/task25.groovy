import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper

def Message processData(Message message) {
    def jsonText = message.getProperty("employeeJson") ?: '{"name":"Taylor","skills":["Communication"]}'
    def data = new JsonSlurper().parseText(jsonText)
    def summary = "Employee ${data.name} has ${data.skills.size()} skill(s)."
    message.setBody(summary)
    message.setProperty("skillCount", data.skills.size())
    return message
}
