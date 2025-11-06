// This demonstration evaluates promotion eligibility with if/else and boolean logic using HR review data.
// It illustrates the conditional patterns from slides 6-7 while continuing the message property techniques from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def years = (message.getProperty("yearsOfService") ?: "0") as Integer
    def rating = (message.getProperty("performanceRating") ?: "0") as Integer
    def eligible = years >= 5 && rating >= 4
    def decision = eligible ? "Eligible for promotion" : "Keep growing through coaching"
    def details = "Years: ${years}, Rating: ${rating}, Decision: ${decision}"
    message.setBody(details)
    message.setProperty("promotionEligible", eligible)
    message.setProperty("promotionDecision", decision)
    return message
}
