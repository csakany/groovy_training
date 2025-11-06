// This demonstration filters colleagues with a closure to spotlight HR teams using list and map data.
// It ties into the closure techniques from slides 14-16 plus the collection operations from slides 17-20 and message handling from slide 47.
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
