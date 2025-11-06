// This demonstration enforces mandatory status properties and throws an error when validation fails.
// It aligns with the error handling advice from slides 51-52 and the broader best-practice guidance on slide 59.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def requiredProperty = message.getProperty("employeeStatus")
    if (!requiredProperty) {
        throw new Exception("employeeStatus property is required for validation.")
    }
    def note = requiredProperty == 'Active' ? 'Employee is active.' : 'Employee is not active.'
    message.setBody(note)
    message.setProperty("statusChecked", true)
    message.setProperty("employeeStatus", requiredProperty)
    return message
}
