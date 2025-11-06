// This demonstration shows how a CPI script can log headers and create an attachment for troubleshooting.
// It highlights the monitoring techniques from slides 53-55 alongside the CPI message helpers on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    Map headers = message.getHeaders() ?: [:]
    String correlationId = headers.get("X-CorrelationId") ?: "Unknown"

    def messageLogFactory = message.getProperty("messageLogFactory")
    def messageLog = messageLogFactory?.getMessageLog(message)
    if (messageLog) {
        def payload = message.getBody() as String
        messageLog.addAttachmentAsString("PayloadSnapshot", payload, "text/plain")
        messageLog.setStringProperty("CorrelationId", correlationId)
        message.setProperty("logAttachmentCreated", true)
    } else {
        message.setProperty("logAttachmentCreated", false)
    }

    message.setBody("Logged header X-CorrelationId: ${correlationId}")
    message.setProperty("loggedHeader", correlationId)
    return message
}
