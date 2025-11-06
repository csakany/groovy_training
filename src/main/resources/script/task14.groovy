// This demonstration manages an onboarding course list to showcase Groovy's list mutation helpers.
// It reflects the list concepts from slides 17-18 and continues practicing message property updates from slide 47.
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
