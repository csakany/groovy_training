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
