import com.sap.gateway.ip.core.customdev.util.Message

abstract class StaffMember {
    String name
    abstract String role()
}

class Intern extends StaffMember {
    @Override
    String role() { "Intern" }
}

class Mentor extends StaffMember {
    @Override
    String role() { "Mentor" }
}

def Message processData(Message message) {
    def intern = new Intern(name: message.getProperty("internName") ?: "Jamie")
    def mentor = new Mentor(name: message.getProperty("mentorName") ?: "Avery")
    def summary = "${intern.name} serves as ${intern.role()}\n${mentor.name} serves as ${mentor.role()}"
    message.setBody(summary)
    message.setProperty("roles", [intern.role(), mentor.role()].join(', '))
    return message
}
