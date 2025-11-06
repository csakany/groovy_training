import com.sap.gateway.ip.core.customdev.util.Message

BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
    // TODO: calculate the salary after applying the raise percent.
    return salary
}

def Message processData(Message message) {
    def instructions = """Practice Task 11: Add a default parameter.\n""" +
            "1. Create a method that increases a salary by a percent.\n" +
            "2. Use a default percent when none is provided.\n" +
            "3. Call the method with and without the optional argument.\n" +
            "4. Share the new salary in the body and a property."
    message.setBody(instructions)
    message.setProperty("nextStep", "Finish the applyRaise method so the math is correct.")
    return message
}
