import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def namesText = message.getProperty("names") ?: ""
    def names = namesText.split(",").collect { it.trim() }.findAll { it }
    def sorted = names.sort()
    message.setBody("Sorted Names: ${sorted.join(', ')}")
    message.setProperty("firstName", sorted ? sorted.first() : "")
    return message
}
