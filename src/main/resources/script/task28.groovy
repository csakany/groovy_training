// This demonstration validates email and phone details with regex patterns for HR data quality.
// It references the regular expression guidance from slides 44-45 along with message property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def email = message.getProperty("email") ?: ""
    def phone = message.getProperty("phone") ?: ""
    def emailValid = email ==~ /[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}/
    def phoneValid = phone ==~ /\+?\d{1,3}[- ]?\d{3}[- ]?\d{4}/
    def summary = "Email valid: ${emailValid}\nPhone valid: ${phoneValid}"
    message.setBody(summary)
    message.setProperty("emailValid", emailValid)
    message.setProperty("phoneValid", phoneValid)
    return message
}
