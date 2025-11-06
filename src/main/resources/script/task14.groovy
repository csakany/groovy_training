import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def courses = ["Orientation", "Benefits Overview"]
    courses << "Safety Training"
    courses.remove("Benefits Overview")
    courses.add(0, "Company Tour")
    message.setBody("Course Plan: ${courses.join(', ')}")
    message.setProperty("courseCount", courses.size())
    message.setProperty("firstCourse", courses.first())
    return message
}
