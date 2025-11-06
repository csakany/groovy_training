import os
import textwrap

TASKS = []

TASKS.append({
    "num": 1,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def name = message.getProperty(\"employeeName\") ?: \"New Hire\"
            def department = message.getProperty(\"department\") ?: \"Company\"
            def greeting = \"Welcome ${name} to the ${department} team!\"
            message.setBody(greeting)
            message.setProperty(\"nameLength\", name.length())
            message.setProperty(\"departmentUpper\", department.toUpperCase())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 1: Create a warm welcome message.\\n\"\"\" +
                    \"1. Read the employee name from the message properties.\\n\" +
                    \"2. Read the department name from the message properties.\\n\" +
                    \"3. Build a sentence like \\\"Welcome Ana to the HR team!\\\" and set it as the body.\\n\" +
                    \"4. Store any helpful extra detail as a property (for example the name length).\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Follow the steps in the body to complete the practice task.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeName\", \"Avery Johnson\")
        msg.setProperty(\"department\", \"Human Resources\")
        """
    ),
})

TASKS.append({
    "num": 2,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def idValue = (message.getProperty(\"employeeId\") ?: \"0\") as Integer
            def salaryValue = (message.getProperty(\"salary\") ?: \"0\") as BigDecimal
            def activeValue = (message.getProperty(\"active\") ?: \"false\").toBoolean()
            def summary = \"\"\"Employee ID: ${idValue}\\nID Type: ${idValue.getClass().simpleName}\\nSalary Type: ${salaryValue.getClass().simpleName}\\nActive Type: ${activeValue.getClass().simpleName}\"\"\"
            message.setBody(summary)
            message.setProperty(\"idType\", idValue.getClass().simpleName)
            message.setProperty(\"salaryType\", salaryValue.getClass().simpleName)
            message.setProperty(\"activeType\", activeValue.getClass().simpleName)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 2: Explore Groovy data types.\\n\"\"\" +
                    \"1. Fetch the employeeId, salary, and active status from properties.\\n\" +
                    \"2. Convert them to useful types (Integer, BigDecimal, Boolean).\\n\" +
                    \"3. Print the converted values and their types to the message body.\\n\" +
                    \"4. Save the type names as properties for later steps.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Convert the values and record their types.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeId\", \"1001\")
        msg.setProperty(\"salary\", \"52000.50\")
        msg.setProperty(\"active\", \"true\")
        """
    ),
})

TASKS.append({
    "num": 3,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def baseSalary = (message.getProperty(\"baseSalary\") ?: \"0\") as BigDecimal
            def bonus = (message.getProperty(\"bonus\") ?: \"0\") as BigDecimal
            def taxRate = (message.getProperty(\"taxRate\") ?: \"0\") as BigDecimal
            def totalComp = baseSalary + bonus
            def netComp = totalComp - (totalComp * taxRate / 100)
            def report = \"\"\"Base Salary: ${baseSalary}\\nBonus: ${bonus}\\nTotal Compensation: ${totalComp}\\nNet After Tax: ${netComp.setScale(2, BigDecimal.ROUND_HALF_UP)}\"\"\"
            message.setBody(report)
            message.setProperty(\"totalComp\", totalComp)
            message.setProperty(\"netComp\", netComp.setScale(2, BigDecimal.ROUND_HALF_UP))
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 3: Calculate employee pay.\\n\"\"\" +
                    \"1. Read the base salary, bonus, and tax rate from properties.\\n\" +
                    \"2. Add salary and bonus together.\\n\" +
                    \"3. Subtract tax from the total to show the net amount.\\n\" +
                    \"4. Present all results clearly in the body and store totals as properties.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use arithmetic operators to fill in the calculations.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"baseSalary\", \"48000\")
        msg.setProperty(\"bonus\", \"3500\")
        msg.setProperty(\"taxRate\", \"15\")
        """
    ),
})

TASKS.append({
    "num": 4,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def years = (message.getProperty(\"yearsOfService\") ?: \"0\") as Integer
            def rating = (message.getProperty(\"performanceRating\") ?: \"0\") as Integer
            def eligible = years >= 5 && rating >= 4
            def decision = eligible ? \"Eligible for promotion\" : \"Keep growing through coaching\"
            def details = \"Years: ${years}, Rating: ${rating}, Decision: ${decision}\"
            message.setBody(details)
            message.setProperty(\"promotionEligible\", eligible)
            message.setProperty(\"promotionDecision\", decision)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 4: Make a promotion decision.\\n\"\"\" +
                    \"1. Fetch yearsOfService and performanceRating from message properties.\\n\" +
                    \"2. Use an if/else statement to decide if the employee qualifies.\\n\" +
                    \"3. Write a short explanation into the message body.\\n\" +
                    \"4. Store the decision as a property called promotionEligible.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Combine comparison and logical operators to reach a decision.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"yearsOfService\", \"6\")
        msg.setProperty(\"performanceRating\", \"4\")
        """
    ),
})

TASKS.append({
    "num": 5,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def contractType = message.getProperty(\"contractType\") ?: \"FullTime\"
            def note
            switch (contractType) {
                case \"FullTime\":
                    note = \"Full-time employees receive full benefits.\"
                    break
                case \"PartTime\":
                    note = \"Part-time employees receive prorated benefits.\"
                    break
                case \"Contractor\":
                    note = \"Contractors collaborate for specific projects.\"
                    break
                default:
                    note = \"Review contract details with HR.\"
            }
            message.setBody(\"Contract Type: ${contractType}\\nNote: ${note}\")
            message.setProperty(\"contractNote\", note)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 5: Use a switch statement.\\n\"\"\" +
                    \"1. Read the contractType property (examples: FullTime, PartTime, Contractor).\\n\" +
                    \"2. With a switch statement, create a helpful explanation for each case.\\n\" +
                    \"3. Write the explanation to the body.\\n\" +
                    \"4. Save the explanation as a property called contractNote.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Map each contract type to a short description.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"contractType\", \"PartTime\")
        """
    ),
})

TASKS.append({
    "num": 6,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def membersText = message.getProperty(\"teamMembers\") ?: \"\"
            def members = membersText.split(\",\").collect { it.trim() }.findAll { it }
            def output = new StringBuilder(\"Team Members:\\n\")
            for (member in members) {
                output.append(\"- ${member}\\n\")
            }
            message.setBody(output.toString().trim())
            message.setProperty(\"memberCount\", members.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 6: Loop through a team list.\\n\"\"\" +
                    \"1. Read a comma separated list of names from teamMembers.\\n\" +
                    \"2. Split the text into individual names.\\n\" +
                    \"3. Use a for loop to print each name on its own line.\\n\" +
                    \"4. Count how many names appear and store the number as memberCount.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use a for loop to walk through each team member.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"teamMembers\", \"Avery, Jordan, Casey, Riley\")
        """
    ),
})

TASKS.append({
    "num": 7,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def modulesText = message.getProperty(\"moduleList\") ?: \"\"
            def modules = modulesText.split(\",\").collect { it.trim() }.findAll { it }
            def steps = []
            int index = 0
            while (index < modules.size()) {
                steps << \"Step ${index + 1}: Complete ${modules[index]}\"
                index++
            }
            message.setBody(steps.join(\"\\n\"))
            message.setProperty(\"moduleCount\", modules.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 7: Build a while loop.\\n\"\"\" +
                    \"1. Read the moduleList property for upcoming training modules.\\n\" +
                    \"2. Turn the text into a list.\\n\" +
                    \"3. Use a while loop to create numbered steps.\\n\" +
                    \"4. Output the steps and count how many modules exist.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Remember to update the loop counter inside the while loop.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"moduleList\", \"Orientation, Compliance Training, Benefits Overview\")
        """
    ),
})

TASKS.append({
    "num": 8,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def hourText = message.getProperty(\"hourLogs\") ?: \"\"
            def hours = hourText.split(\",\").collect { it.trim() }.findAll { it }.collect { it as Integer }
            def total = hours.sum() ?: 0
            def average = hours ? total / hours.size() : 0
            def report = \"\"\"Recorded Hours: ${hours}\\nTotal Hours: ${total}\\nAverage Per Day: ${String.format('%.1f', average)}\"\"\"
            message.setBody(report)
            message.setProperty(\"totalHours\", total)
            message.setProperty(\"averageHours\", String.format('%.1f', average))
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 8: Use collection helpers.\\n\"\"\" +
                    \"1. Read daily hour values from hourLogs.\\n\" +
                    \"2. Convert them into integers using collect.\\n\" +
                    \"3. Calculate the total and the average.\\n\" +
                    \"4. Show the numbers in the body and store totals as properties.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use split and collect to prepare the list before calling sum().\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"hourLogs\", \"8, 7, 9, 8\")
        """
    ),
})

TASKS.append({
    "num": 9,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            try {
                def request = message.getProperty(\"requestedRaise\") ?: \"0\"
                def raisePercent = request as BigDecimal
                message.setBody(\"Requested raise: ${raisePercent}%\")
                message.setProperty(\"raiseValid\", true)
            } catch (Exception ex) {
                message.setBody(\"Invalid raise value provided.\")
                message.setProperty(\"raiseValid\", false)
                message.setProperty(\"errorMessage\", ex.message)
            } finally {
                message.setProperty(\"checkedValue\", message.getProperty(\"requestedRaise\"))
            }
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 9: Catch bad input.\\n\"\"\" +
                    \"1. Read the requestedRaise property.\\n\" +
                    \"2. Try to convert it to a number.\\n\" +
                    \"3. If conversion fails, show a friendly error message.\\n\" +
                    \"4. Use a finally block to record that the check was performed.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Wrap the conversion in a try/catch block.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"requestedRaise\", \"7.5\")
        """
    ),
})

TASKS.append({
    "num": 10,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        String buildSummary(String name, String role) {
            return \"${name} currently works as ${role}.\"
        }

        def Message processData(Message message) {
            def name = message.getProperty(\"employeeName\") ?: \"Colleague\"
            def role = message.getProperty(\"jobTitle\") ?: \"Team Member\"
            def summary = buildSummary(name, role)
            message.setBody(summary)
            message.setProperty(\"summaryLength\", summary.length())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        String buildSummary(String name, String role) {
            // TODO: return a helpful summary sentence.
            return \"Implement me\"
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 10: Write and call a method.\\n\"\"\" +
                    \"1. Define a method that accepts a name and a job title.\\n\" +
                    \"2. Return a short sentence that combines both values.\\n\" +
                    \"3. Call the method inside processData.\\n\" +
                    \"4. Store the length of the final sentence as summaryLength.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Replace the placeholder return value in buildSummary.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeName\", \"Dana Lopez\")
        msg.setProperty(\"jobTitle\", \"HR Specialist\")
        """
    ),
})

TASKS.append({
    "num": 11,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
            return salary + (salary * percent / 100)
        }

        def Message processData(Message message) {
            def salary = (message.getProperty(\"currentSalary\") ?: \"0\") as BigDecimal
            def percentText = message.getProperty(\"raisePercent\")
            BigDecimal newSalary = percentText ? applyRaise(salary, (percentText as BigDecimal)) : applyRaise(salary)
            message.setBody(\"New salary after raise: ${newSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}\")
            message.setProperty(\"updatedSalary\", newSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        BigDecimal applyRaise(BigDecimal salary, BigDecimal percent = 5G) {
            // TODO: calculate the salary after applying the raise percent.
            return salary
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 11: Add a default parameter.\\n\"\"\" +
                    \"1. Create a method that increases a salary by a percent.\\n\" +
                    \"2. Use a default percent when none is provided.\\n\" +
                    \"3. Call the method with and without the optional argument.\\n\" +
                    \"4. Share the new salary in the body and a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Finish the applyRaise method so the math is correct.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"currentSalary\", \"42000\")
        msg.setProperty(\"raisePercent\", \"4\")
        """
    ),
})

TASKS.append({
    "num": 12,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def namesText = message.getProperty(\"names\") ?: \"\"
            def names = namesText.split(\",\").collect { it.trim() }.findAll { it }
            def greet = { String person -> \"Hello ${person}, welcome to the team!\" }
            def greetings = names.collect(greet)
            message.setBody(greetings.join(\"\\n\"))
            message.setProperty(\"greetingsCount\", greetings.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 12: Use a closure.\\n\"\"\" +
                    \"1. Read a list of names.\\n\" +
                    \"2. Create a closure that receives one name and returns a greeting.\\n\" +
                    \"3. Use collect with the closure to build greeting lines.\\n\" +
                    \"4. Count how many greetings were created.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Closures can be stored in variables and reused.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"names\", \"Avery, Morgan, Taylor\")
        """
    ),
})

TASKS.append({
    "num": 13,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def targetDept = message.getProperty(\"targetDept\") ?: \"HR\"
            def colleagues = [
                [name: 'Avery', dept: 'HR'],
                [name: 'Jordan', dept: 'IT'],
                [name: 'Casey', dept: 'HR'],
                [name: 'Morgan', dept: 'Finance']
            ]
            def matcher = { person -> person.dept == targetDept }
            def matches = colleagues.findAll(matcher)
            def lines = matches.collect { \"${it.name} works in ${it.dept}.\" }
            message.setBody(lines ? lines.join(\"\\n\") : \"No colleagues found in ${targetDept}.\")
            message.setProperty(\"matchCount\", matches.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 13: Filter with a closure.\\n\"\"\" +
                    \"1. Create a small list of maps containing name and dept.\\n\" +
                    \"2. Read targetDept from the message.\\n\" +
                    \"3. Use findAll with a closure to keep matching colleagues.\\n\" +
                    \"4. Print the matches or a friendly \\\"not found\\\" message.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Closures can check each map and return true or false.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"targetDept\", \"HR\")
        """
    ),
})

TASKS.append({
    "num": 14,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def courses = [\"Orientation\", \"Benefits Overview\"]
            courses << \"Safety Training\"
            courses.remove(\"Benefits Overview\")
            courses.add(0, \"Company Tour\")
            message.setBody(\"Course Plan: ${courses.join(', ')}\")
            message.setProperty(\"courseCount\", courses.size())
            message.setProperty(\"firstCourse\", courses.first())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 14: Work with lists.\\n\"\"\" +
                    \"1. Start with a list of onboarding courses.\\n\" +
                    \"2. Add a new course to the end.\\n\" +
                    \"3. Remove a course that is not needed.\\n\" +
                    \"4. Show the updated plan and the total count.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Remember that << adds items and remove() deletes them.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        """
    ),
})

TASKS.append({
    "num": 15,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def headcount = [HR: 4, Finance: 6, IT: 8]
            headcount.HR = headcount.HR + 1
            headcount['Learning'] = 3
            def reportLines = headcount.collect { dept, count -> \"${dept}: ${count}\" }
            message.setBody(reportLines.join(\"\\n\"))
            message.setProperty(\"totalDepartments\", headcount.size())
            message.setProperty(\"hrHeadcount\", headcount.HR)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 15: Manage a map.\\n\"\"\" +
                    \"1. Create a map of departments and their headcount.\\n\" +
                    \"2. Update one of the existing values.\\n\" +
                    \"3. Add a brand new department.\\n\" +
                    \"4. Print each entry on its own line.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use map.key = value to update entries.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        """
    ),
})

TASKS.append({
    "num": 16,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def trainingDays = (1..5).collect { day -> \"Day ${day}: Onboarding activity\" }
            message.setBody(trainingDays.join(\"\\n\"))
            message.setProperty(\"dayRange\", \"1..5\")
            message.setProperty(\"totalDays\", trainingDays.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 16: Use ranges.\\n\"\"\" +
                    \"1. Create a numeric range such as 1..5.\\n\" +
                    \"2. Turn the range into a list of onboarding day messages.\\n\" +
                    \"3. Print the list as multiple lines.\\n\" +
                    \"4. Store the total number of days as a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Ranges can be iterated just like lists.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        """
    ),
})

TASKS.append({
    "num": 17,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class EmployeeProfile {
            String name
            String department
        }

        def Message processData(Message message) {
            def profile = new EmployeeProfile(
                    name: message.getProperty(\"employeeName\") ?: \"Alex\",
                    department: message.getProperty(\"department\") ?: \"HR\")
            def summary = \"${profile.name} works in ${profile.department}.\"
            message.setBody(summary)
            message.setProperty(\"profileClass\", profile.getClass().simpleName)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class EmployeeProfile {
            String name
            String department
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 17: Define a class.\\n\"\"\" +
                    \"1. Create a class with name and department fields.\\n\" +
                    \"2. Build an instance using message properties.\\n\" +
                    \"3. Print a short description about the employee.\\n\" +
                    \"4. Record the class name in a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Classes bundle related information together.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeName\", \"Priya Singh\")
        msg.setProperty(\"department\", \"People Operations\")
        """
    ),
})

TASKS.append({
    "num": 18,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class PayrollEmployee {
            String name
            BigDecimal salary
            BigDecimal applyRaise(BigDecimal percent) {
                salary += salary * percent / 100
                return salary
            }
        }

        def Message processData(Message message) {
            def employee = new PayrollEmployee(
                    name: message.getProperty(\"employeeName\") ?: \"Colleague\",
                    salary: (message.getProperty(\"currentSalary\") ?: \"0\") as BigDecimal)
            def percent = (message.getProperty(\"raisePercent\") ?: \"0\") as BigDecimal
            def updatedSalary = employee.applyRaise(percent)
            def summary = \"${employee.name}'s salary is now ${updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP)}\"
            message.setBody(summary)
            message.setProperty(\"updatedSalary\", updatedSalary.setScale(2, BigDecimal.ROUND_HALF_UP))
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class PayrollEmployee {
            String name
            BigDecimal salary
            BigDecimal applyRaise(BigDecimal percent) {
                // TODO: update salary using the percent value.
                return salary
            }
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 18: Add behavior to a class.\\n\"\"\" +
                    \"1. Create a class with a salary field.\\n\" +
                    \"2. Implement a method that increases the salary.\\n\" +
                    \"3. Call the method and show the new salary.\\n\" +
                    \"4. Save the updated value in a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Update the salary field before returning from applyRaise.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeName\", \"Kai Brown\")
        msg.setProperty(\"currentSalary\", \"51000\")
        msg.setProperty(\"raisePercent\", \"6\")
        """
    ),
})

TASKS.append({
    "num": 19,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class TeamMember {
            String name
            void introduce(StringBuilder output) {
                output.append(\"I am ${name}.\\n\")
            }
        }

        class TeamLead extends TeamMember {
            String team
            @Override
            void introduce(StringBuilder output) {
                super.introduce(output)
                output.append(\"I lead the ${team} team.\\n\")
            }
        }

        def Message processData(Message message) {
            def lead = new TeamLead(name: message.getProperty(\"employeeName\") ?: \"Alex\", team: message.getProperty(\"team\") ?: \"HR\")
            def output = new StringBuilder()
            lead.introduce(output)
            message.setBody(output.toString().trim())
            message.setProperty(\"role\", \"TeamLead\")
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        class TeamMember {
            String name
            void introduce(StringBuilder output) {
                // TODO: append a simple introduction.
            }
        }

        class TeamLead extends TeamMember {
            String team
            @Override
            void introduce(StringBuilder output) {
                // TODO: call super.introduce and add a line about the team.
            }
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 19: Extend a class.\\n\"\"\" +
                    \"1. Create a base class with an introduce method.\\n\" +
                    \"2. Extend it with a TeamLead that adds more detail.\\n\" +
                    \"3. Use super to keep the original message.\\n\" +
                    \"4. Print the full introduction.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Override introduce() in the TeamLead subclass.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeName\", \"Lena Torres\")
        msg.setProperty(\"team\", \"Talent Development\")
        """
    ),
})

TASKS.append({
    "num": 20,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        abstract class StaffMember {
            String name
            abstract String role()
        }

        class Intern extends StaffMember {
            @Override
            String role() { \"Intern\" }
        }

        class Mentor extends StaffMember {
            @Override
            String role() { \"Mentor\" }
        }

        def Message processData(Message message) {
            def intern = new Intern(name: message.getProperty(\"internName\") ?: \"Jamie\")
            def mentor = new Mentor(name: message.getProperty(\"mentorName\") ?: \"Avery\")
            def summary = \"${intern.name} serves as ${intern.role()}\\n${mentor.name} serves as ${mentor.role()}\"
            message.setBody(summary)
            message.setProperty(\"roles\", [intern.role(), mentor.role()].join(', '))
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        abstract class StaffMember {
            String name
            abstract String role()
        }

        class Intern extends StaffMember {
            // TODO: return a role description.
            String role() { \"TBD\" }
        }

        class Mentor extends StaffMember {
            // TODO: return a role description.
            String role() { \"TBD\" }
        }

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 20: Implement abstract classes.\\n\"\"\" +
                    \"1. Create an abstract StaffMember with a role method.\\n\" +
                    \"2. Implement concrete subclasses for Intern and Mentor.\\n\" +
                    \"3. Instantiate each subclass with a name.\\n\" +
                    \"4. Show their role descriptions in the body.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Replace the placeholder return values with real text.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"internName\", \"Sam Reed\")
        msg.setProperty(\"mentorName\", \"Amira Cho\")
        """
    ),
})

TASKS.append({
    "num": 21,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def xmlText = message.getBody(String)
            def parsed = new XmlSlurper().parseText(xmlText)
            def manager = parsed.User[0]
            def name = manager.displayName.text()
            def division = manager.division.text()
            def summary = \"Manager: ${name}\\nDivision: ${division}\"
            message.setBody(summary)
            message.setProperty(\"managerName\", name)
            message.setProperty(\"managerDivision\", division)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 21: Parse XML basics.\\n\"\"\" +
                    \"1. Read the XML body using XmlSlurper.\\n\" +
                    \"2. Navigate to the first User element.\\n\" +
                    \"3. Pull out the displayName and division values.\\n\" +
                    \"4. Show the values in the body and store them as properties.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use element.text() to read XML values.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        def xmlContent = new File('../../data/in/manager.xml').text
        msg.setBody(xmlContent)
        """
    ),
})

TASKS.append({
    "num": 22,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def xmlText = message.getBody(String)
            def parsed = new XmlSlurper().parseText(xmlText)
            def manager = parsed.User[0]
            def email = manager.email.text()
            def phone = manager.businessPhone.text()
            def summary = \"Contact Email: ${email}\\nPhone: ${phone}\"
            message.setBody(summary)
            message.setProperty(\"contactEmail\", email)
            message.setProperty(\"contactPhone\", phone)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 22: Read more XML values.\\n\"\"\" +
                    \"1. Reuse XmlSlurper to parse the message body.\\n\" +
                    \"2. Find the email and businessPhone elements.\\n\" +
                    \"3. Write the contact information to the body.\\n\" +
                    \"4. Store both values as properties for later steps.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"The same parsed XML can deliver many fields.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        def xmlContent = new File('../../data/in/manager.xml').text
        msg.setBody(xmlContent)
        """
    ),
})

TASKS.append({
    "num": 23,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def xmlText = message.getBody(String)
            def parsed = new XmlSlurper().parseText(xmlText)
            def companies = parsed.FOCompany.FOCompany
            def firstFive = companies.take(5).collect { company ->
                \"${company.externalCode.text()} - ${company.name.text()}\"
            }
            def summary = firstFive.join(\"\\n\")
            message.setBody(summary)
            message.setProperty(\"listedCompanies\", firstFive.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 23: Loop through XML nodes.\\n\"\"\" +
                    \"1. Parse the DM_Training.xml content from the body.\\n\" +
                    \"2. Collect the first few FOCompany entries.\\n\" +
                    \"3. Build lines that show the code and the name.\\n\" +
                    \"4. Write the lines to the body.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use take(5) to keep the list short.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        def xmlContent = new File('../../data/in/DM_Training.xml').text
        msg.setBody(xmlContent)
        """
    ),
})

TASKS.append({
    "num": 24,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def xmlText = message.getBody(String)
            def parsed = new XmlSlurper().parseText(xmlText)
            def companies = parsed.FOCompany.FOCompany.take(3)
            def header = \"Code,Name\"
            def rows = companies.collect { company -> \"${company.externalCode.text()},${company.name.text()}\" }
            def csv = ([header] + rows).join(\"\\n\")
            message.setBody(csv)
            message.setProperty(\"csvRows\", rows.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.xml.XmlSlurper

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 24: Transform XML to CSV.\\n\"\"\" +
                    \"1. Parse the XML document.\\n\" +
                    \"2. Select a few FOCompany entries.\\n\" +
                    \"3. Build a CSV string with a header row.\\n\" +
                    \"4. Set the CSV content as the body.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Remember to join the header and rows with new lines.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        def xmlContent = new File('../../data/in/DM_Training.xml').text
        msg.setBody(xmlContent)
        """
    ),
})

TASKS.append({
    "num": 25,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.json.JsonSlurper

        def Message processData(Message message) {
            def jsonText = message.getProperty(\"employeeJson\") ?: '{"name":"Taylor","skills":["Communication"]}'
            def data = new JsonSlurper().parseText(jsonText)
            def summary = \"Employee ${data.name} has ${data.skills.size()} skill(s).\"
            message.setBody(summary)
            message.setProperty(\"skillCount\", data.skills.size())
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.json.JsonSlurper

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 25: Parse JSON.\\n\"\"\" +
                    \"1. Read the employeeJson property.\\n\" +
                    \"2. Parse it with JsonSlurper.\\n\" +
                    \"3. Access a couple of fields such as name and skills.\\n\" +
                    \"4. Display the information in the body.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"JsonSlurper returns maps and lists that feel like Groovy objects.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeJson\", '{"name":"Taylor","skills":["Communication","Coaching"]}')
        """
    ),
})

TASKS.append({
    "num": 26,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.json.JsonSlurper
        import groovy.json.JsonOutput

        def Message processData(Message message) {
            def jsonText = message.getProperty(\"employeeJson\") ?: '{"name":"Sky","salary":40000}'
            def data = new JsonSlurper().parseText(jsonText)
            data.salary = (data.salary as BigDecimal) + 2000
            def updatedJson = JsonOutput.prettyPrint(JsonOutput.toJson(data))
            message.setBody(updatedJson)
            message.setProperty(\"updatedSalary\", data.salary)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message
        import groovy.json.JsonSlurper
        import groovy.json.JsonOutput

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 26: Modify JSON content.\\n\"\"\" +
                    \"1. Parse the employeeJson property.\\n\" +
                    \"2. Update one of the values (for example salary).\\n\" +
                    \"3. Convert the structure back to JSON text.\\n\" +
                    \"4. Set the pretty printed JSON as the body.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"JsonOutput.toJson turns Groovy maps back into JSON.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeJson\", '{"name":"Sky","salary":41000}')
        """
    ),
})

TASKS.append({
    "num": 27,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def namesText = message.getProperty(\"names\") ?: \"\"
            def names = namesText.split(\",\").collect { it.trim() }.findAll { it }
            def sorted = names.sort()
            message.setBody(\"Sorted Names: ${sorted.join(', ')}\")
            message.setProperty(\"firstName\", sorted ? sorted.first() : \"\")
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 27: Sort collections.\\n\"\"\" +
                    \"1. Read a comma separated list of names.\\n\" +
                    \"2. Turn it into a list.\\n\" +
                    \"3. Use the sort() method to order the names alphabetically.\\n\" +
                    \"4. Print the sorted result.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"You can call sort() directly on the list.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"names\", \"Zoe, Amir, Lina, Ben\")
        """
    ),
})

TASKS.append({
    "num": 28,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def email = message.getProperty(\"email\") ?: \"\"
            def phone = message.getProperty(\"phone\") ?: \"\"
            def emailValid = email ==~ /[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}/
            def phoneValid = phone ==~ /\+?\d{1,3}[- ]?\d{3}[- ]?\d{4}/
            def summary = \"Email valid: ${emailValid}\\nPhone valid: ${phoneValid}\"
            message.setBody(summary)
            message.setProperty(\"emailValid\", emailValid)
            message.setProperty(\"phoneValid\", phoneValid)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 28: Validate with regular expressions.\\n\"\"\" +
                    \"1. Read the email and phone properties.\\n\" +
                    \"2. Create regex checks for each value.\\n\" +
                    \"3. Print whether each value is valid.\\n\" +
                    \"4. Store the boolean results as properties.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Use ==~ to check if the entire string matches the pattern.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"email\", \"new.hire@example.com\")
        msg.setProperty(\"phone\", \"+1-202-555-0173\")
        """
    ),
})

TASKS.append({
    "num": 29,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def employeeId = message.getHeader(\"EmployeeId\")
            def defaultBody = message.getBody(String) ?: \"\"
            if (!employeeId) {
                message.setBody(\"Missing EmployeeId header.\")
                message.setProperty(\"headerPresent\", false)
                return message
            }
            def report = \"Processing update for employee ${employeeId}.\\nPayload: ${defaultBody}\"
            message.setBody(report)
            message.setProperty(\"headerPresent\", true)
            message.setProperty(\"processedEmployee\", employeeId)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 29: Read headers and properties.\\n\"\"\" +
                    \"1. Fetch a header such as EmployeeId.\\n\" +
                    \"2. Check if the header exists before using it.\\n\" +
                    \"3. Build a short summary that includes the body text.\\n\" +
                    \"4. Store whether the header was present as a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Remember that headers and properties can both be null.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"Annual review data\")
        msg.setHeader(\"EmployeeId\", \"101045\")
        """
    ),
})

TASKS.append({
    "num": 30,
    "demo": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def requiredProperty = message.getProperty(\"employeeStatus\")
            if (!requiredProperty) {
                throw new Exception(\"employeeStatus property is required for validation.\")
            }
            def note = requiredProperty == 'Active' ? 'Employee is active.' : 'Employee is not active.'
            message.setBody(note)
            message.setProperty(\"statusChecked\", true)
            message.setProperty(\"employeeStatus\", requiredProperty)
            return message
        }
        """
    ),
    "practice": textwrap.dedent(
        """
        import com.sap.gateway.ip.core.customdev.util.Message

        def Message processData(Message message) {
            def instructions = \"\"\"Practice Task 30: Validate required data.\\n\"\"\" +
                    \"1. Check that a property like employeeStatus exists.\\n\" +
                    \"2. Throw an exception with a clear message when it is missing.\\n\" +
                    \"3. When the value is present, write a short status note.\\n\" +
                    \"4. Store that the validation ran by setting a property.\"
            message.setBody(instructions)
            message.setProperty(\"nextStep\", \"Provide helpful error messages when throwing exceptions.\")
            return message
        }
        """
    ),
    "test_setup": textwrap.dedent(
        """
        msg.setBody(\"\")
        msg.setProperty(\"employeeStatus\", \"Active\")
        """
    ),
})

DEMO_TEMPLATE = "{demo}\n"
PRACTICE_TEMPLATE = "{practice}\n"

TEST_TEMPLATE = textwrap.dedent(
    """
    import com.sap.gateway.ip.core.customdev.util.Message
    import org.apache.camel.CamelContext
    import org.apache.camel.Exchange
    import org.apache.camel.impl.DefaultCamelContext
    import org.apache.camel.support.DefaultExchange

    GroovyShell shell = new GroovyShell()
    Script script = shell.parse(new File('{script_path}'))
    CamelContext context = new DefaultCamelContext()
    Exchange exchange = new DefaultExchange(context)
    Message msg = new Message(exchange)
    {test_setup}
    script.processData(msg)
    exchange.getIn().setBody(msg.getBody())
    println("Body:\\n${{msg.getBody(String)}}")
    println('Properties:')
    msg.getProperties().each {{ k, v -> println("$k = $v") }}
    """
)


def write_file(path, content):
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, 'w', encoding='utf-8') as handle:
        handle.write(content.strip() + '\n')


def main():
    for task in TASKS:
        num = task['num']
        demo_path = f"src/main/resources/script/task{num}.groovy"
        practice_path = f"src/main/resources/script/task{num}_todo.groovy"
        demo_content = DEMO_TEMPLATE.format(demo=task['demo'].strip())
        practice_content = PRACTICE_TEMPLATE.format(practice=task['practice'].strip())
        write_file(demo_path, demo_content)
        write_file(practice_path, practice_content)

        demo_test_path = f"src/test/task{num}_test.groovy"
        practice_test_path = f"src/test/task{num}_todo_test.groovy"
        test_setup = task['test_setup'].strip()
        demo_test_content = TEST_TEMPLATE.format(
            script_path=f"../../src/main/resources/script/task{num}.groovy",
            test_setup=test_setup
        )
        practice_test_content = TEST_TEMPLATE.format(
            script_path=f"../../src/main/resources/script/task{num}_todo.groovy",
            test_setup=test_setup
        )
        write_file(demo_test_path, demo_test_content)
        write_file(practice_test_path, practice_test_content)


if __name__ == '__main__':
    main()

