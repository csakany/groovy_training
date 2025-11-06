// This demonstration creates a simple EmployeeProfile class to bundle HR data with object instances.
// It references the class fundamentals on slides 23-24 together with message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class EmployeeProfile {
    String name
    String department
}

def Message processData(Message message) {
    def profile = new EmployeeProfile(
            name: message.getProperty("employeeName") ?: "Alex",
            department: message.getProperty("department") ?: "HR")
    def summary = "${profile.name} works in ${profile.department}."
    message.setBody(summary)
    message.setProperty("profileClass", profile.getClass().simpleName)
    return message
}
