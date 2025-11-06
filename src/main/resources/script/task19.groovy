import com.sap.gateway.ip.core.customdev.util.Message

class TeamMember {
    String name
    void introduce(StringBuilder output) {
        output.append("I am ${name}.\n")
    }
}

class TeamLead extends TeamMember {
    String team
    @Override
    void introduce(StringBuilder output) {
        super.introduce(output)
        output.append("I lead the ${team} team.\n")
    }
}

def Message processData(Message message) {
    def lead = new TeamLead(name: message.getProperty("employeeName") ?: "Alex", team: message.getProperty("team") ?: "HR")
    def output = new StringBuilder()
    lead.introduce(output)
    message.setBody(output.toString().trim())
    message.setProperty("role", "TeamLead")
    return message
}
