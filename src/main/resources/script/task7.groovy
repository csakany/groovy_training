// This demonstration builds numbered training steps with a while loop to emphasize iterative processing in HR plans.
// It reinforces the loop control concepts from slide 8 along with message property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def modulesText = message.getProperty("moduleList") ?: ""
    def modules = modulesText.split(",").collect { it.trim() }.findAll { it }
    def steps = []
    int index = 0
    while (index < modules.size()) {
        steps << "Step ${index + 1}: Complete ${modules[index]}"
        index++
    }
    message.setBody(steps.join("\n"))
    message.setProperty("moduleCount", modules.size())
    return message
}
