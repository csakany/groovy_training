import com.sap.gateway.ip.core.customdev.util.Message
import groovy.json.JsonSlurper
import groovy.json.JsonOutput

def Message processData(Message message) {
    // 1) Read JSON from body
    String jsonText = message.getBody()
    if (!jsonText?.trim()) {
        throw new IllegalArgumentException("Message body must contain CE JSON as a String.")
    }

    // 2) Parse JSON to native structures
    def root = new JsonSlurper().parseText(jsonText)

    // Normalize: get the CompoundEmployee list
    List ceList
    if (root instanceof Map && root.CompoundEmployee instanceof List) {
        ceList = root.CompoundEmployee
    } else if (root instanceof List) {
        ceList = root
    } else {
        throw new IllegalStateException("JSON does not contain a CompoundEmployee array.")
    }

    // 3) PART A — Read & reshape: build compact directory from "current" job (end_date=9999-12-31 if present)
    List<Map> directory = []
    ceList.each { ce ->
        def person   = ce.person ?: [:]
        def personal = person.personal_information ?: [:]
        def email    = person.email_information ?: [:]
        def jobs     = (person.employment_information?.job_information ?: []) as List

        def currentJob = jobs.find { (it?.end_date ?: '') == '9999-12-31' } ?: (jobs ? jobs[0] : [:])

        directory << [
                id        : (ce.id ?: "").toString(),
                personId  : (person.person_id_external ?: "").toString(),
                name      : (personal.formal_name ?: "${personal.first_name ?: ''} ${personal.last_name ?: ''}").trim(),
                email     : (email.email_address ?: "").toString(),
                division  : (currentJob.division ?: "").toString(),
                jobTitle  : (currentJob.job_title ?: "").toString(),
                managerId : (currentJob.manager_id ?: "").toString(),
                location  : (currentJob.location ?: "").toString()
        ]
    }

    def compact = [ count: directory.size(), employees: directory ]

    // 4) PART B — In-place mutate original JSON
    //    (a) Lowercase email domains
    //    (b) Prefix phone numbers with "+44-" if no leading "+"
    //    (c) Add person.extracted = true
    ceList.each { ce ->
        def person = ce.person ?: [:]

        // (a) lowercase domain
        def emailInfo = person.email_information
        if (emailInfo && emailInfo.email_address instanceof String) {
            def v = emailInfo.email_address
            def at = v.indexOf('@')
            if (at > 0 && at < v.length() - 1) {
                emailInfo.email_address = v.substring(0, at) + '@' + v.substring(at + 1).toLowerCase()
            }
        }

        // (b) phone prefix
        def phoneInfo = person.phone_information
        if (phoneInfo && phoneInfo.phone_number instanceof String) {
            def num = phoneInfo.phone_number
            if (num && !num.startsWith('+')) {
                phoneInfo.phone_number = "+44-" + num
            }
        }

        // (c) add extracted flag
        if (!(person instanceof Map)) {
            ce.person = [:] + (person ?: [:])
        }
        ce.person.extracted = true
    }

    // 5) Compose final result with both views
    def result = [
            compact : compact, // the reshaped directory
            mutated : root     // the original tree after mutations
    ]

    String outJson = JsonOutput.prettyPrint(JsonOutput.toJson(result))
    message.setBody(outJson)
    message.setProperty("employeeCount", directory.size())
    return message
}
