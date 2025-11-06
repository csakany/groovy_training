// This demonstration uses a Groovy switch to tailor HR contract guidance for different worker types.
// It showcases the flexible switch logic presented on slide 6 together with the message usage practices from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def contractType = message.getProperty("contractType") ?: "FullTime"
    def note
    switch (contractType) {
        case "FullTime":
            note = "Full-time employees receive full benefits."
            break
        case "PartTime":
            note = "Part-time employees receive prorated benefits."
            break
        case "Contractor":
            note = "Contractors collaborate for specific projects."
            break
        default:
            note = "Review contract details with HR."
    }
    message.setBody("Contract Type: ${contractType}\nNote: ${note}")
    message.setProperty("contractNote", note)
    return message
}
