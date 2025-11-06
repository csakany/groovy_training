import com.sap.gateway.ip.core.customdev.util.Message

class EmployeeProfile {
    String name
    String department
}

def Message processData(Message message) {
    def instructions = """Practice Task 17: Define a class.\n""" +
            "1. Create a class with name and department fields.\n" +
            "2. Build an instance using message properties.\n" +
            "3. Print a short description about the employee.\n" +
            "4. Record the class name in a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Classes bundle related information together.")
    return message
}
