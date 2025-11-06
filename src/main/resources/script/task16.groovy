import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def trainingDays = (1..5).collect { day -> "Day ${day}: Onboarding activity" }
    message.setBody(trainingDays.join("\n"))
    message.setProperty("dayRange", "1..5")
    message.setProperty("totalDays", trainingDays.size())
    return message
}
