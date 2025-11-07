// This demonstration compares full if/else-if/else blocks with inline ternary expressions for HR promotion reviews.
// It follows the branching strategies from slides 6-7 while reinforcing CPI property handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    // getProperty looks up message-scoped metadata, while the Elvis operator falls back to a default string when missing.
    def name = message.getProperty("employeeName") ?: "Colleague"
    // (value as Integer) parses the property text into a primitive-friendly Integer for comparisons.
    int years = (message.getProperty("yearsOfService") ?: "0") as Integer
    int rating = (message.getProperty("performanceRating") ?: "0") as Integer

    String decision
    if (years >= 5 && rating >= 4) {
        decision = "Promote ${name} this cycle."
    } else if (rating >= 3) {
        decision = "Offer mentorship and revisit next cycle."
    } else {
        decision = "Create an improvement plan with ${name}."
    }

    boolean fastTrack = years >= 7 && rating >= 5
    // The ternary operator condition ? trueValue : falseValue keeps the fast-track message concise for reviewers.
    String fastTrackNote = fastTrack ? "Eligible for fast-track recognition." : "Standard review cadence applies."

    def summary = """Employee: ${name}\nYears of Service: ${years}\nRating: ${rating}\nDecision: ${decision}\n${fastTrackNote}"""
    // setBody replaces the payload shown to downstream steps, while setProperty keeps supplemental data handy.
    message.setBody(summary)
    message.setProperty("promotionDecision", decision)
    message.setProperty("fastTrack", fastTrack)
    message.setProperty("fastTrackNote", fastTrackNote)
    return message
}

/*
Practice Task 4:
- Read employee name, performance rating, and tenure from message properties.
- Recreate the control flow above to choose between promotion, mentoring, or improvement actions.
- Add a ternary expression that stores a fast-track note when the criteria are met.
- Keep the focus on if/else-if/else and ternary flow as shown in the demonstration.
*/
