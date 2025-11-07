// This demonstration defines a reusable custom function that can be called from CPI message mappings to format names.
// It connects to the mapping customization ideas from slides 56-57 while reinforcing CPI message usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

// buildDisplayName collects non-empty fragments and returns them joined with spaces.
String buildDisplayName(String title, String firstName, String lastName) {
    // collect { it?.trim() } trims each component and safely handles nulls before findAll removes blanks.
    def parts = [title, firstName, lastName].collect { it?.trim() }.findAll { it }
    // join(' ') concatenates the cleaned parts to form the final display name.
    parts.join(' ')
}

def Message processData(Message message) {
    def title = message.getProperty("title") ?: ""
    def firstName = message.getProperty("firstName") ?: "Alex"
    def lastName = message.getProperty("lastName") ?: "Kim"

    def fullName = buildDisplayName(title, firstName, lastName)
    // setBody shares the assembled name, and setProperty makes it available to subsequent steps.
    message.setBody("Full name: ${fullName}")
    message.setProperty("fullName", fullName)
    return message
}

/*
Practice Task 27:
- Create a helper method that assembles a display name from title, first name, and last name just like buildDisplayName.
- Use it inside processData, place the result in the body, and store it as a property for downstream mapping steps.
*/
