// This demonstration extracts repeated logic into a helper method to describe employee roles concisely.
// It connects to the method structure guidance on slides 12-13 and the message manipulation basics on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

String buildSummary(String name, String role) {
    return "${name} currently works as ${role}."
}

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "Colleague"
    def role = message.getProperty("jobTitle") ?: "Team Member"
    def summary = buildSummary(name, role)
    message.setBody(summary)
    message.setProperty("summaryLength", summary.length())
    return message
}
