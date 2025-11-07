// This demonstration classifies training progress with a Groovy switch that handles ranges and defaults for HR reporting.
// It expands on the switch examples from slide 6 while applying CPI message patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    // Casting with as Integer converts the percentage text into a number we can compare.
    Integer completion = (message.getProperty("completionPercent") ?: "0") as Integer
    String status
    // switch evaluates the completion value once and walks through range cases like 0..49 until it finds a match.
    switch (completion) {
        case 0..49:
            status = "Learning just started."
            break
        case 50..89:
            status = "Training is on track."
            break
        case 90..100:
            status = "Training completed."
            break
        default:
            status = "Review completion entry with the team."
    }

    // setBody writes the human-readable summary, while setProperty shares the status with later steps.
    message.setBody("Completion: ${completion}%\nStatus: ${status}")
    message.setProperty("trainingStatus", status)
    return message
}
