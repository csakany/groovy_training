import com.sap.gateway.ip.core.customdev.util.Message

abstract class StaffMember {
    String name
    abstract String role()
}

class Intern extends StaffMember {
    // TODO: return a role description.
    String role() { "TBD" }
}

class Mentor extends StaffMember {
    // TODO: return a role description.
    String role() { "TBD" }
}

def Message processData(Message message) {
    def instructions = """Practice Task 20: Implement abstract classes.\n""" +
            "1. Create an abstract StaffMember with a role method.\n" +
            "2. Implement concrete subclasses for Intern and Mentor.\n" +
            "3. Instantiate each subclass with a name.\n" +
            "4. Show their role descriptions in the body."
    message.setBody(instructions)
    message.setProperty("nextStep", "Replace the placeholder return values with real text.")
    return message
}
