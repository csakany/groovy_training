// This demonstration defines a closure that personalizes greetings for new hires pulled from message properties.
// It highlights the closure features explained on slides 14-16 and the CPI message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def namesText = message.getProperty("names") ?: ""
    // split and collect/trim prepare the list so each entry can be greeted individually.
    def names = namesText.split(",").collect { it.trim() }.findAll { it }
    // The closure greet = { String person -> ... } captures reusable logic that formats each welcome line.
    def greet = { String person -> "Hello ${person}, welcome to the team!" }
    // collect(greet) invokes the closure for every name to build the greeting list.
    def greetings = names.collect(greet)
    // join("\n") stitches the lines together with newlines before setBody publishes them.
    message.setBody(greetings.join("\n"))
    message.setProperty("greetingsCount", greetings.size())
    return message
}
