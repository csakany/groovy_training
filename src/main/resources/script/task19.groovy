// This demonstration models inheritance so a Task19TeamLead extends Task19TeamMember and customizes introductions.
// It aligns with the inheritance and overriding lessons from slides 25-26 while reinforcing message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class Task19TeamMember {
    String name
    // introduce(StringBuilder) writes a basic introduction into the provided buffer using append.
    void introduce(StringBuilder output) {
        output.append("I am ${name}.\n")
    }
}

class Task19TeamLead extends Task19TeamMember {
    String team
    @Override
    void introduce(StringBuilder output) {
        // super.introduce(output) reuses the base behavior before adding the leader-specific line.
        super.introduce(output)
        output.append("I lead the ${team} team.\n")
    }
}

def Message processData(Message message) {
    // The Groovy map constructor sets both the inherited name and the team property in one statement.
    def lead = new Task19TeamLead(name: message.getProperty("employeeName") ?: "Alex", team: message.getProperty("team") ?: "HR")
    // new StringBuilder() creates a mutable buffer the introduce methods can append to.
    def output = new StringBuilder()
    lead.introduce(output)
    // toString() materializes the combined introduction and trim() removes the trailing newline.
    message.setBody(output.toString().trim())
    message.setProperty("role", "TeamLead")
    return message
}
