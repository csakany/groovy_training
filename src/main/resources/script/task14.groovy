// This demonstration manages an onboarding course list to showcase Groovy's list mutation helpers.
// It reflects the list concepts from slides 17-18 and continues practicing message property updates from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def courses = ["Orientation", "Benefits Overview"]
    // The leftShift operator (<<) appends a new item to the list in-place.
    courses << "Safety Training"
    // remove(value) deletes the first matching element from the list.
    courses.remove("Benefits Overview")
    // add(index, value) inserts a course at a specific position so the tour happens first.
    courses.add(0, "Company Tour")
    // join(', ') produces a comma-separated string for the body, while size() and first() expose handy metadata.
    message.setBody("Course Plan: ${courses.join(', ')}")
    message.setProperty("courseCount", courses.size())
    message.setProperty("firstCourse", courses.first())
    return message
}
