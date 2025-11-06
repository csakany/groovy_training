// This demonstration loops through a comma-separated list of teammates to create a readable roster for HR orientation.
// It applies the for-each loop techniques from slides 8-9 and the message property handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def membersText = message.getProperty("teamMembers") ?: ""
    def members = membersText.split(",").collect { it.trim() }.findAll { it }
    def output = new StringBuilder("Team Members:\n")
    for (member in members) {
        output.append("- ${member}\n")
    }
    message.setBody(output.toString().trim())
    message.setProperty("memberCount", members.size())
    return message
}
