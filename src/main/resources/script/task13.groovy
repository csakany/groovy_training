import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def targetDept = message.getProperty("targetDept") ?: "HR"
    def colleagues = [
        [name: 'Avery', dept: 'HR'],
        [name: 'Jordan', dept: 'IT'],
        [name: 'Casey', dept: 'HR'],
        [name: 'Morgan', dept: 'Finance']
    ]
    def matcher = { person -> person.dept == targetDept }
    def matches = colleagues.findAll(matcher)
    def lines = matches.collect { "${it.name} works in ${it.dept}." }
    message.setBody(lines ? lines.join("\n") : "No colleagues found in ${targetDept}.")
    message.setProperty("matchCount", matches.size())
    return message
}
