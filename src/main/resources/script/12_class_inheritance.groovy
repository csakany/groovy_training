// INHERITANCE (extends)

// STEP 1️⃣: Define the base class (Parent)
println "\nSTEP 1: Define a base class 'BaseStaff'"

class BaseStaff {
    String name
    String department
    double salary

    // Constructor
    BaseStaff(String name, String department, double salary) {
        this.name = name
        this.department = department
        this.salary = salary
    }

    // Method: calculate base bonus
    double calculateBonus() {
        return salary * 0.10
    }

    // Method: describe staff
    String describe() {
        return "BaseStaff: $name | Dept: $department | Salary: ${String.format('%.2f', salary)}"
    }
}

println "Base class 'BaseStaff' defined successfully."


// STEP 2️⃣: Define a subclass (Child) that extends BaseStaff

class TeamLead extends BaseStaff {
    int teamSize

    // Subclass constructor — call super() to initialize parent properties
    TeamLead(String name, String department, double salary, int teamSize) {
        super(name, department, salary)
        this.teamSize = teamSize
    }

    // Override parent method
    @Override
    double calculateBonus() {
        def baseBonus = super.calculateBonus()
        // TeamLead earns extra 5% if team > 5
        if (teamSize > 5) {
            baseBonus += salary * 0.05
        }
        return baseBonus
    }

    // Add new method specific to TeamLead
    String teamInfo() {
        return "$name leads a team of $teamSize people."
    }

    // Override describe()
    @Override
    String describe() {
        return "TeamLead: $name | Dept: $department | Salary: ${String.format('%.2f', salary)} | Team size: $teamSize"
    }
}

println "Subclass 'TeamLead' defined successfully."


// STEP 3️⃣: Create objects from both classes
println "\nSTEP 3: Create BaseStaff and TeamLead objects"

def staff = new BaseStaff("Anna Schmidt", "IT", 6000)
def lead  = new TeamLead("Thomas Müller", "Sales", 8000, 8)

println "Created BaseStaff: ${staff.describe()}"
println "Created TeamLead:  ${lead.describe()}"


// STEP 4️⃣: Demonstrate inherited methods
println "\nSTEP 4: Demonstrate inherited methods"
println "BaseStaff bonus: ${String.format('%.2f', staff.calculateBonus())}"
println "TeamLead bonus:  ${String.format('%.2f', lead.calculateBonus())}"


// STEP 5️⃣: Demonstrate overridden behavior
println "\nSTEP 5: Show method overriding (TeamLead has custom bonus logic)"
println "If TeamLead manages more than 5 people, extra 5% bonus is added."


// STEP 6️⃣: Demonstrate new method in subclass
println "\nSTEP 6: Use subclass-specific method"
println lead.teamInfo()



// -------------------------------------------------------------------------
// 💡 PRACTICE TASKS:
//
// 1️⃣ Add another subclass: 'Intern' that extends BaseStaff.
//     - Add a property mentorName.
//     - Override calculateBonus() to always return 0.
//
// 2️⃣ Modify TeamLead so that if teamSize > 10 → extra 10% bonus.
//
// 3️⃣ Add getRole() method to BaseStaff returning "Staff".
//     Override in TeamLead to return "Team Lead".
// -------------------------------------------------------------------------
