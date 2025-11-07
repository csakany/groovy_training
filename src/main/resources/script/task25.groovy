// This demonstration parses and updates employee JSON to count skills and adjust salary in one pass.
// It reflects the JSON parsing and transformation approach from slides 36-40 alongside the CPI message patterns on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    def jsonText = message.getProperty("employeeJson") ?: '{"name":"Taylor","skills":["Communication"],"salary":40000}'
    // JsonSlurper.parseText turns the JSON string into a map-like structure we can mutate.
    def data = new JsonSlurper().parseText(jsonText)

    // (data.skills ?: []) ensures size() always runs on a list by falling back to an empty collection.
    int skillCount = (data.skills ?: []).size()
    if (data.salary != null) {
        // as BigDecimal converts the salary to a decimal before adding 2000 and setScale formats it with two decimals.
        data.salary = ((data.salary as BigDecimal) + 2000).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

    // JsonOutput.toJson re-serializes the map and prettyPrint formats it nicely for logs.
    def updatedJson = JsonOutput.prettyPrint(JsonOutput.toJson(data))
    def salaryText = data.salary != null ? data.salary : "Not provided"
    def summary = """Employee ${data.name} has ${skillCount} skill(s).\nUpdated Salary: ${salaryText}"""

    // setProperty exposes both the counts and the prettified JSON for downstream use.
    message.setBody(summary)
    message.setProperty("skillCount", skillCount)
    message.setProperty("updatedSalary", salaryText)
    message.setProperty("updatedJson", updatedJson)
    return message
}

/*
Practice Task 25:
- Load the employee JSON from a property with JsonSlurper, count the skills, and adjust the salary in the same way.
- Write a summary to the body and store both the counts and updated JSON as properties for downstream steps.
*/
