// Reusable Methods with Default Parameters

// 1️⃣ Define a reusable method with parameters
def calculateBonus(salary, performance = "standard", years = 1) {
    def bonusRate
    switch (performance) {
        case "excellent": bonusRate = 0.20; break
        case "good":      bonusRate = 0.15; break
        case "standard":  bonusRate = 0.10; break
        default:          bonusRate = 0.05
    }

    // Extra 1% per year after 3 years
    def experienceBonus = (years > 3) ? (years - 3) * 0.01 : 0
    def totalRate = bonusRate + experienceBonus

    return salary * totalRate
}

// 2️⃣ Define a method with a default argument
def greetEmployee(name = "Employee", department = "General") {
    return "Hello ${name}, welcome to the ${department} department!"
}

// 3️⃣ Define a method that calls others
def summarizeEmployee(name, salary, performance = "standard", years = 1) {
    def greeting = greetEmployee(name)
    def bonus = calculateBonus(salary, performance, years)
    def total = salary + bonus

    return """
Employee Summary
----------------
Name: ${name}
Base Salary: \$${String.format("%.2f", salary)}
Performance: ${performance}
Years in Company: ${years}
Calculated Bonus: \$${String.format("%.2f", bonus)}
Total Compensation: \$${String.format("%.2f", total)}
"""
}

// ===== Using the methods =====
println greetEmployee("Thomas Müller", "Sales")
println greetEmployee()  // uses default parameters

def bonus1 = calculateBonus(5000, "excellent", 5)
println "\nCalculated bonus for Thomas: \$${String.format("%.2f", bonus1)}"

def summary = summarizeEmployee("Anna Schmidt", 4500, "good", 4)
println summary


// -------------------------------------------------------------------------
// 💡 PRACTICE TASK:
//
// 1️⃣ Add a new method: calculateTax(totalSalary, rate = 0.25)
//     - It should return salary * rate.
//
// 2️⃣ Modify summarizeEmployee() to include tax and net salary.
//
// 3️⃣ Try calling summarizeEmployee() with and without performance/year arguments
//     to observe how default parameters behave.
// -------------------------------------------------------------------------
