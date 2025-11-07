// This demonstration wraps property parsing in try/catch blocks to model safe HR score handling.
// It illustrates the exception concepts from slides 10-11 together with CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def ratingText = message.getProperty("engagementScore") ?: "unknown"
    Integer score
    String note
    try {
        // as Integer attempts to coerce the string rating into a numeric score we can compare.
        score = ratingText as Integer
        // The ternary operator quickly chooses a note based on the score threshold.
        note = score >= 80 ? "High engagement" : "Monitor engagement"
    } catch (NumberFormatException ex) {
        score = null
        note = "Engagement score missing or invalid."
    } finally {
        // finally always executes, so setProperty here records the original text for auditing.
        message.setProperty("rawScore", ratingText)
    }

    def summary = score ? "Score: ${score}\nNote: ${note}" : note
    // setBody publishes either the combined score summary or the validation warning.
    message.setBody(summary)
    message.setProperty("engagementScore", score)
    message.setProperty("engagementNote", note)
    return message
}

/*
Practice Task 11:
- Read an engagement score (or similar metric) from properties and attempt to convert it to an Integer within a try block.
- Branch on the parsed value to craft a note, or fall back gracefully in the catch block when parsing fails.
- Record both the raw entry and the explanatory note as properties following the example above.
*/
