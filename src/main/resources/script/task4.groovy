// This demonstration compares full if/else-if/else blocks with inline ternary expressions for HR promotion reviews.
// It follows the branching strategies from slides 6-7 while reinforcing CPI property handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "Colleague"
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
    String fastTrackNote = fastTrack ? "Eligible for fast-track recognition." : "Standard review cadence applies."

    def summary = """Employee: ${name}\nYears of Service: ${years}\nRating: ${rating}\nDecision: ${decision}\n${fastTrackNote}"""
    message.setBody(summary)
    message.setProperty("promotionDecision", decision)
    message.setProperty("fastTrack", fastTrack)
    message.setProperty("fastTrackNote", fastTrackNote)
    return message
}
