
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {

    // --------------------------------------------------------------
    // STEP 1️⃣: Get and set the message BODY
    // --------------------------------------------------------------
    def originalBody = message.getBody() as String  // retrieve body as text

    def newBody = "Modified payload: " + originalBody?.toUpperCase()
    message.setBody(newBody)

    // --------------------------------------------------------------
    // STEP 2️⃣: Get and set message PROPERTIES
    // --------------------------------------------------------------
    // CPI message properties are often used to store dynamic values
    def existingProp = message.getProperty("country")    // may be null if not set

    message.setProperty("country", "Germany")
    message.setProperty("year", 2025)

    // --------------------------------------------------------------
    // STEP 3️⃣: Get and set message HEADERS
    // --------------------------------------------------------------
    def headerBefore = message.getHeader("Content-Type", String)

    message.setHeader("Content-Type", "text/plain; charset=UTF-8")
    message.setHeader("Process-ID", "HR-DEMO-001")

    return message
}
