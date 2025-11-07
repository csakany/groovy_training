// This demonstration creates a simple Task17EmployeeProfile class to bundle HR data with object instances.
// It references the class fundamentals on slides 23-24 together with message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

class Task17EmployeeProfile {
    String name
    String department
}

def Message processData(Message message) {
    // new Task17EmployeeProfile(name: ..., department: ...) uses Groovy's map-style constructor to populate fields.
    def profile = new Task17EmployeeProfile(
            name: message.getProperty("employeeName") ?: "Alex",
            department: message.getProperty("department") ?: "HR")
    def summary = "${profile.name} works in ${profile.department}."
    // getClass().simpleName returns the runtime type so downstream steps see which class built the data.
    message.setBody(summary)
    message.setProperty("profileClass", profile.getClass().simpleName)
    return message
}
