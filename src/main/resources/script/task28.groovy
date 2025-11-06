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
