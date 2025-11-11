// Looping techniques in Groovy

// Sample data
def employees = ["Anna", "Ben", "Clara", "David"]
def modules   = ["Welcome", "Safety", "Compliance", "Diversity"]

// -------------------------------------------------------------
// 1️⃣ Classic FOR LOOP (like in Java)
for (int i = 0; i < employees.size(); i++) {
    println "Employee #${i + 1}: ${employees[i]}"
}

// -------------------------------------------------------------
// 2️⃣ FOR-IN LOOP (Groovy style)
for (employee in employees) {
    println "Welcome, ${employee}!"
}

// -------------------------------------------------------------
// 3️⃣ EACH() LOOP (functional style)
employees.each { emp ->
    println "Assign basic onboarding to ${emp}"
}

// -------------------------------------------------------------
// 4️⃣ EACHWITHINDEX() LOOP (functional with index)
modules.eachWithIndex { module, index ->
    println "Module ${index + 1}: ${module}"
}

// -------------------------------------------------------------
// 5️⃣ WHILE LOOP
int count = 0
while (count < employees.size()) {
    println "Processing record for: ${employees[count]}"
    count++
}

// -------------------------------------------------------------
// 6️⃣ COMBINED EXAMPLE (nested loops)
for (employee in employees) {
    println "\nTraining plan for ${employee}:"
    modules.eachWithIndex { module, i ->
        println "  Step ${i + 1}: Complete ${module} training"
    }
}


// -------------------------------------------------------------------------
// 💡 PRACTICE TASK 11:
//
// 1️⃣ Use a for loop to print only employees whose name length > 4.
// 2️⃣ Use eachWithIndex to number training modules starting from 101.
// 3️⃣ Create a while loop that stops when it finds the module "Compliance".
// 4️⃣ Bonus: Combine for-in and each() to print all employees with all modules.
// -------------------------------------------------------------------------
