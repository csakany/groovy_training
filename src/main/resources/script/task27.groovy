// This demonstration sorts a list of employee names to prepare clean HR rosters.
// It links to the collection sorting strategies on slides 42-43 while continuing the message handling patterns from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def namesText = message.getProperty("names") ?: ""
    def names = namesText.split(",").collect { it.trim() }.findAll { it }
    def sorted = names.sort()
    message.setBody("Sorted Names: ${sorted.join(', ')}")
    message.setProperty("firstName", sorted ? sorted.first() : "")
    return message
}
