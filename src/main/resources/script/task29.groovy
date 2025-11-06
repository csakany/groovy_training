// This demonstration checks for a required EmployeeId header before processing to emphasize safe message access.
// It highlights the message context best practices from slides 47-48 and the defensive programming reminders from slide 59.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def employeeId = message.getHeader("EmployeeId")
    def defaultBody = message.getBody(String) ?: ""
    if (!employeeId) {
        message.setBody("Missing EmployeeId header.")
        message.setProperty("headerPresent", false)
        return message
    }
    def report = "Processing update for employee ${employeeId}.\nPayload: ${defaultBody}"
    message.setBody(report)
    message.setProperty("headerPresent", true)
    message.setProperty("processedEmployee", employeeId)
    return message
}
