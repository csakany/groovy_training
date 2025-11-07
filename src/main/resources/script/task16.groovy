// This demonstration builds a training schedule from a numeric range to simplify HR planning.
// It draws on the range features presented on slides 21-22 while using message properties per slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    // (1..5) creates an inclusive range object, and collect transforms each day into a descriptive line.
    def trainingDays = (1..5).collect { day -> "Day ${day}: Onboarding activity" }
    // join("\n") outputs one line per day while size() reports how many entries were generated.
    message.setBody(trainingDays.join("\n"))
    message.setProperty("dayRange", "1..5")
    message.setProperty("totalDays", trainingDays.size())
    return message
}
