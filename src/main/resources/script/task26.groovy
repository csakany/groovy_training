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
