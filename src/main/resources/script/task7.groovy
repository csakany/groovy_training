// This demonstration uses collection helpers like collect and findAll to organize HR training session statuses.
// It connects to the collection operations on slides 17-18 while reusing the CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def sessionsText = message.getProperty("sessions") ?: ""
    def sessions = sessionsText.split(",").collect { it.trim() }.findAll { it }
            .collect { entry ->
                def parts = entry.split("\\|")
                [name: parts[0], status: parts.length > 1 ? parts[1] : "Scheduled"]
            }

    def completed = sessions.findAll { it.status.equalsIgnoreCase("Completed") }
    def upcoming = sessions.findAll { !it.status.equalsIgnoreCase("Completed") }

    def summary = new StringBuilder()
    summary.append("Completed Sessions:\n")
    completed.each { summary.append("- ${it.name}\n") }
    summary.append("\nUpcoming Sessions:\n")
    upcoming.each { summary.append("- ${it.name} (${it.status})\n") }

    message.setBody(summary.toString().trim())
    message.setProperty("completedCount", completed.size())
    message.setProperty("upcomingCount", upcoming.size())
    return message
}
