// This demonstration updates a salary inside a JSON payload and re-serializes it for downstream systems.
// It reinforces the JSON transformation pattern from slides 39-40 together with message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def jsonText = message.getProperty("employeeJson") ?: '{"name":"Sky","salary":40000}'
    def data = new JsonSlurper().parseText(jsonText)
    data.salary = (data.salary as BigDecimal) + 2000
    def updatedJson = JsonOutput.prettyPrint(JsonOutput.toJson(data))
    message.setBody(updatedJson)
    message.setProperty("updatedSalary", data.salary)
    return message
}
