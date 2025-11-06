import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def namesText = message.getProperty("names") ?: ""
    def names = namesText.split(",").collect { it.trim() }.findAll { it }
    def greet = { String person -> "Hello ${person}, welcome to the team!" }
    def greetings = names.collect(greet)
    message.setBody(greetings.join("\n"))
    message.setProperty("greetingsCount", greetings.size())
    return message
}
