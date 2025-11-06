// This demonstration defines a reusable custom function that can be called from CPI message mappings to format names.
// It connects to the mapping customization ideas from slides 56-57 while reinforcing CPI message usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

String buildDisplayName(String title, String firstName, String lastName) {
    def parts = [title, firstName, lastName].collect { it?.trim() }.findAll { it }
    parts.join(' ')
}

def Message processData(Message message) {
    def title = message.getProperty("title") ?: ""
    def firstName = message.getProperty("firstName") ?: "Alex"
    def lastName = message.getProperty("lastName") ?: "Kim"

    def fullName = buildDisplayName(title, firstName, lastName)
    message.setBody("Full name: ${fullName}")
    message.setProperty("fullName", fullName)
    return message
}
