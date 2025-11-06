// This demonstration formats HR badge details with string helpers and interpolation to reinforce Groovy text basics.
// It builds on the string handling ideas from slides 3-5 and continues the CPI message access from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "Team Member"
    def jobTitle = message.getProperty("jobTitle") ?: "Associate"
    def location = message.getProperty("location") ?: "Remote"

    def initials = name.split(' ').findAll { it }.collect { it[0] }.join('').toUpperCase()
    def badge = """Employee Badge\nName: ${name}\nRole: ${jobTitle}\nLocation: ${location}\nInitials: ${initials}"""

    message.setBody(badge)
    message.setProperty("badgeLabel", "${name} - ${jobTitle}")
    message.setProperty("employeeInitials", initials)
    return message
}
