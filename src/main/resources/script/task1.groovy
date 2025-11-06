// This demonstration pulls HR onboarding properties from the CPI message and builds a welcome message with Groovy string interpolation.
// It reinforces the variables and data type fundamentals from slides 2-3 and the CPI message access patterns from slide 47 of the training deck.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def name = message.getProperty("employeeName") ?: "New Hire"
    def department = message.getProperty("department") ?: "Company"
    def greeting = "Welcome ${name} to the ${department} team!"
    message.setBody(greeting)
    message.setProperty("nameLength", name.length())
    message.setProperty("departmentUpper", department.toUpperCase())
    return message
}
