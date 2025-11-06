// This demonstration wraps property parsing in try/catch blocks to model safe HR score handling.
// It illustrates the exception concepts from slides 10-11 together with CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def ratingText = message.getProperty("engagementScore") ?: "unknown"
    Integer score
    String note
    try {
        score = ratingText as Integer
        note = score >= 80 ? "High engagement" : "Monitor engagement"
    } catch (NumberFormatException ex) {
        score = null
        note = "Engagement score missing or invalid."
    } finally {
        message.setProperty("rawScore", ratingText)
    }

    def summary = score ? "Score: ${score}\nNote: ${note}" : note
    message.setBody(summary)
    message.setProperty("engagementScore", score)
    message.setProperty("engagementNote", note)
    return message
}
