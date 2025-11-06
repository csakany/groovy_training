// This demonstration parses employee JSON to count skills for HR capability tracking.
// It reflects the JSON parsing workflow from slides 36-38 while using message properties per slide 47.
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
