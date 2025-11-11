
// CLASS DEMONSTRATION IN GROOVY



class Employee {
    //Define class properties (variables)
    String name
    String department
    double salary
    int years

    //Create a constructor to initialize the object
    Employee(String name, String department, double salary, int years = 1) {
        this.name = name
        this.department = department
        this.salary = salary
        this.years = years
    }

    //Add a method to calculate bonus
    double calculateBonus(String performance = "standard") {
        double rate
        switch (performance) {
            case "excellent": rate = 0.20; break
            case "good":      rate = 0.15; break
            case "standard":  rate = 0.10; break
            default:          rate = 0.05
        }

        // Add an extra 1% for each year after 3 years
        def extra = (years > 3) ? (years - 3) * 0.01 : 0
        return salary * (rate + extra)
    }

    //Add another method to increase salary
    void raiseSalary(double percent = 5.0) {
        this.salary += this.salary * (percent / 100)
    }

    // Override toString() to make printing nice
    String toString() {
        return "Employee(name=$name, dept=$department, salary=${String.format('%.2f', salary)}, years=$years)"
    }
}

println "Class 'Employee' defined successfully."


// STEP 7️⃣: Create instances (objects) of the class
println "\nSTEP 7: Create Employee objects"

def emp1 = new Employee("Thomas Müller", "Sales", 5000, 5)
def emp2 = new Employee("Anna Schmidt", "IT", 6000, 2)
def emp3 = new Employee("Peter Weber", "HR", 4500) // uses default years = 1

println "Employee objects created:"
println emp1
println emp2
println emp3


// Call a method (calculateBonus)
println "\nSTEP 8: Call the 'calculateBonus()' method"

def bonus1 = emp1.calculateBonus("excellent")
def bonus2 = emp2.calculateBonus("good")
def bonus3 = emp3.calculateBonus()

println "${emp1.name} bonus: ${String.format('%.2f', bonus1)}"
println "${emp2.name} bonus: ${String.format('%.2f', bonus2)}"
println "${emp3.name} bonus: ${String.format('%.2f', bonus3)}"


// Call another method (raiseSalary)
println "\nSTEP 9: Call the 'raiseSalary()' method"

emp1.raiseSalary(10)   // 10% raise
emp2.raiseSalary()     // uses default 5% raise
println "After raises:"
println emp1
println emp2


// Combine everything — show total compensation
println "\nSTEP 10: Calculate total compensation (salary + bonus)"
def total1 = emp1.salary + emp1.calculateBonus("excellent")
def total2 = emp2.salary + emp2.calculateBonus("good")

println "${emp1.name}'s total compensation: ${String.format('%.2f', total1)}"
println "${emp2.name}'s total compensation: ${String.format('%.2f', total2)}"


// -------------------------------------------------------------------------
// 💡 PRACTICE TASKS:
//
// 1️⃣ Add a new method getTotalCompensation() that returns salary + bonus.
//
// 2️⃣ Create a subclass 'Manager' that extends Employee.
//     - Add a property teamSize
//     - Override calculateBonus() to add an extra 5% if teamSize > 5.
//
// 3️⃣ Create a Manager object and demonstrate the overridden method.
// --------
