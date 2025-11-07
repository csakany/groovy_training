// This demonstration shows how a CPI script can log headers and create an attachment for troubleshooting.
// It highlights the monitoring techniques from slides 53-55 alongside the CPI message helpers on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    // getHeaders() returns a map of adapter headers; the Elvis operator falls back to an empty map when none exist.
    Map headers = message.getHeaders() ?: [:]
    // headers.get("X-CorrelationId") fetches a specific header value for tracking.
    String correlationId = headers.get("X-CorrelationId") ?: "Unknown"

    // message.getProperty("messageLogFactory") retrieves the CPI logging helper when the runtime provides it.
    def messageLogFactory = message.getProperty("messageLogFactory")
    def messageLog = messageLogFactory?.getMessageLog(message)
    if (messageLog) {
        // getBody() reads the current payload, and addAttachmentAsString stores it as a text attachment in the log.
        def payload = message.getBody() as String
        messageLog.addAttachmentAsString("PayloadSnapshot", payload, "text/plain")
        // setStringProperty saves correlation metadata alongside the attachment.
        messageLog.setStringProperty("CorrelationId", correlationId)
        message.setProperty("logAttachmentCreated", true)
    } else {
        message.setProperty("logAttachmentCreated", false)
    }

    // setBody and setProperty capture the correlation ID outcome for downstream visibility.
    message.setBody("Logged header X-CorrelationId: ${correlationId}")
    message.setProperty("loggedHeader", correlationId)
    return message
}

/*
Practice Task 26:
- Read a useful header such as X-CorrelationId, log the payload as an attachment when messageLogFactory is available, and record whether the snapshot was created.
- Echo the header in the body and store the values as properties, following the monitoring pattern above.
*/
