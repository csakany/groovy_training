// This demonstration combines for-each and while loops to organize HR orientation rosters and training steps.
// It reinforces the iterative techniques from slides 8-9 alongside the CPI property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def membersText = message.getProperty("teamMembers") ?: ""
    def modulesText = message.getProperty("moduleList") ?: ""
    // split(",") breaks comma-separated lists, collect { it.trim() } removes extra spaces, and findAll { it } drops blanks.
    def members = membersText.split(",").collect { it.trim() }.findAll { it }
    def modules = modulesText.split(",").collect { it.trim() }.findAll { it }

    // new StringBuilder("...") creates a mutable buffer so append(...) can efficiently add lines.
    def roster = new StringBuilder("Team Members:\n")
    for (member in members) {
        roster.append("- ${member}\n")
    }

    def steps = new StringBuilder("Training Steps:\n")
    int index = 0
    // while (index < modules.size()) loops until the index reaches the module list length reported by size().
    while (index < modules.size()) {
        steps.append("${index + 1}. Complete ${modules[index]}\n")
        index++
    }

    // toString() materializes the buffer text, and trim() removes the trailing newline before publishing.
    def body = (roster.toString() + "\n" + steps.toString()).trim()
    message.setBody(body)
    message.setProperty("memberCount", members.size())
    message.setProperty("moduleCount", modules.size())
    return message
}

/*
Practice Task 6:
- Read comma-separated team members and training modules from properties.
- Use a for-each loop to list members and a while loop to number modules just like above.
- Publish the combined summary in the body and track counts as properties.
*/
