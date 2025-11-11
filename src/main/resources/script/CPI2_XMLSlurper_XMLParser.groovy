import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper
import groovy.util.XmlParser
import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil

def Message processData(Message message) {
    // ------------------------------------------------------------
    // Input: Body holds the CE_response.xml content as String
    // ------------------------------------------------------------
    String xmlText = message.getBody()

    // ------------------------------------------------------------
    // PART A — XmlSlurper: read & reshape into a compact directory
    // ------------------------------------------------------------
    def slurper = new XmlSlurper(false, false)         // validating=false, namespaceAware=false
    def root = slurper.parseText(xmlText)              // GPathResult

    // iterate CompoundEmployee nodes and pick "current" job_information (end_date = 9999-12-31 if present)
    List<Map> directory = []
    root.CompoundEmployee.each { ce ->
        def person = ce.person
        def personal = person.personal_information
        def email = person.email_information
        def jobs = person.employment_information.job_information

        def currentJob = jobs.find { it.end_date?.text() == '9999-12-31' } ?: jobs[0]

        directory << [
                id        : ce.id.text(),
                personId  : person.person_id_external.text(),
                name      : personal.formal_name.text() ?: "${personal.first_name.text()} ${personal.last_name.text()}".trim(),
                email     : email.email_address.text(),
                division  : currentJob.division.text(),
                jobTitle  : currentJob.job_title.text(),
                managerId : currentJob.manager_id.text(),
                location  : currentJob.location.text()
        ]
    }

    // Build a NEW XML structure from extracted data
    def compactBuilder = new StreamingMarkupBuilder()
    def compactXml = compactBuilder.bind {
        mkp.xmlDeclaration()
        Employees(count: directory.size().toString()) {
            directory.each { row ->
                Employee(id: row.id, userId: row.personId) {
                    Name(row.name)
                    Email(row.email)
                    Division(row.division)
                    JobTitle(row.jobTitle)
                    ManagerId(row.managerId)
                    Location(row.location)
                }
            }
        }
    }.toString()

    // ------------------------------------------------------------
    // PART B — XmlParser: in-place mutation on original tree
    // ------------------------------------------------------------
    // Example edits:
    //  1) Ensure all email domains are lowercased
    //  2) Prefix all business phone numbers with '+44-' (demo)
    //  3) Add a <extracted>true</extracted> flag under each person
    def parser = new XmlParser(false, false)           // same flags as above
    Node parsedRoot = parser.parseText(xmlText)

    // email_information
    parsedRoot.'**'
            .grep(Node)                                  // ✅ ensure only Nodes
            .findAll { it.name() == 'email_information' }
            .each { Node ei ->
                Node addr = ei.children().find { it instanceof Node && it.name() == 'email_address' } as Node
                if (addr?.text()) {
                    def val = addr.text()
                    def at = val.indexOf('@')
                    if (at > 0 && at < val.length()-1) {
                        addr.value = val.substring(0, at) + '@' + val.substring(at+1).toLowerCase()
                    }
                }
            }

// phone_information
    parsedRoot.'**'
            .grep(Node)
            .findAll { it.name() == 'phone_information' }
            .each { Node pi ->
                Node num = pi.children().find { it instanceof Node && it.name() == 'phone_number' } as Node
                if (num?.text() && !num.text().startsWith('+')) {
                    num.value = '+44-' + num.text()
                }
            }

// person
    parsedRoot.'**'
            .grep(Node)
            .findAll { it.name() == 'person' }
            .each { Node person ->
                def already = person.children().find { it instanceof Node && it.name() == 'extracted' }
                if (!already) {
                    person.appendNode('extracted', 'true')
                }
            }


    // Serialize the mutated original
    String mutatedXml = XmlUtil.serialize(parsedRoot)  // pretty-prints

    // ------------------------------------------------------------
    // FINAL — Compose an output body that shows both results
    // ------------------------------------------------------------
    // You can choose either compactXml OR mutatedXml as the payload.
    // Below we include both so you can see the difference.
    def outBuilder = new StreamingMarkupBuilder()
    String outXml = outBuilder.bind {
        mkp.xmlDeclaration()
        Result() {
            mkp.comment "Generated by XmlSlurper (compact directory)"
            mkp.yieldUnescaped(compactXml)   // embed the compact directory
            mkp.comment "Original, mutated in-place via XmlParser"
            OriginalMutated() {
                // wrap mutated to keep output a single well-formed document
                mkp.yieldUnescaped(mutatedXml)
            }
        }
    }.toString()

    message.setBody(outXml)
    message.setHeader('Content-Type', 'application/xml; charset=UTF-8')
    message.setProperty('employeeCount', directory.size())
    return message
}
