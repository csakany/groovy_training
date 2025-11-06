// This demonstration combines for-each and while loops to organize HR orientation rosters and training steps.
// It reinforces the iterative techniques from slides 8-9 alongside the CPI property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def membersText = message.getProperty("teamMembers") ?: ""
    def modulesText = message.getProperty("moduleList") ?: ""
    def members = membersText.split(",").collect { it.trim() }.findAll { it }
    def modules = modulesText.split(",").collect { it.trim() }.findAll { it }

    def roster = new StringBuilder("Team Members:\n")
    for (member in members) {
        roster.append("- ${member}\n")
    }

    def steps = new StringBuilder("Training Steps:\n")
    int index = 0
    while (index < modules.size()) {
        steps.append("${index + 1}. Complete ${modules[index]}\n")
        index++
    }

    def body = (roster.toString() + "\n" + steps.toString()).trim()
    message.setBody(body)
    message.setProperty("memberCount", members.size())
    message.setProperty("moduleCount", modules.size())
    return message
}
