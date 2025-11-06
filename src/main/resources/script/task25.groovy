// This demonstration parses and updates employee JSON to count skills and adjust salary in one pass.
// It reflects the JSON parsing and transformation approach from slides 36-40 alongside the CPI message patterns on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def jsonText = message.getProperty("employeeJson") ?: '{"name":"Taylor","skills":["Communication"],"salary":40000}'
    def data = new JsonSlurper().parseText(jsonText)

    int skillCount = (data.skills ?: []).size()
    if (data.salary != null) {
        data.salary = ((data.salary as BigDecimal) + 2000).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

    def updatedJson = JsonOutput.prettyPrint(JsonOutput.toJson(data))
    def salaryText = data.salary != null ? data.salary : "Not provided"
    def summary = """Employee ${data.name} has ${skillCount} skill(s).\nUpdated Salary: ${salaryText}"""

    message.setBody(summary)
    message.setProperty("skillCount", skillCount)
    message.setProperty("updatedSalary", salaryText)
    message.setProperty("updatedJson", updatedJson)
    return message
}
