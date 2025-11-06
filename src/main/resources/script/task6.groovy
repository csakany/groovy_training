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
