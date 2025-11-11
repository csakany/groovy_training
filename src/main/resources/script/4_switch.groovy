//switch/case in Groovy

// Sample employee data (German values)
def employee = [
        name      : "Thomas Müller",
        department: "Sales",   // Sales
        years     : 6,
        performanceScore: 92
]


// Switch example – base bonus by department
def bonus

switch (employee.department) {
    case "Sales":
        bonus = 12
        if (employee.performanceScore >= 90) {
            bonus += 3 // Extra for top performance
        }
        break

    case "IT":
        bonus = 10
        if (employee.performanceScore >= 90 && employee.years > 5) {
            bonus += 2 // Loyalty bonus for senior IT staff
        }
        break


    case "Finance": // Finance
        bonus = 9
        if (employee.performanceScore >= 88) {
            bonus += 3
        }
        break

    default:
        bonus = 5
        println "Department not listed — assigning default bonus."
}

println "\nCalculated Bonus: ${bonus}%"

// Recognition based on performance score
def recognition

switch (employee.performanceScore) {
    case 95..100:
        recognition = "Gold Achievement"
        break
    case 85..94:
        recognition = "Excellent Contributor"
        break
    case 70..84:
        recognition = "Solid Performer"
        break
    default:
        recognition = "Needs Improvement"
}

println "Recognition: ${recognition}"

// Seniority recognition using switch with boolean cases
switch (true) {
    case employee.years >= 15:
        println "Award: Platinum Loyalty Award"
        break
    case employee.years >= 10:
        println "Award: Lifetime Service Award"
        break
    case employee.years >= 5:
        println "Award: Long Service Recognition"
        break
    default:
        println "Award: None yet"
}


// -------------------------------------------------------------------------
// 💡 TASK FOR THE AUDIENCE:
//
// 1️⃣ Add a new department: "Marketing"
//     - Base bonus = 7
//     - If performanceScore >= 90, bonus += 4
//
// 2️⃣ Add a new recognition level:
//     - If performanceScore < 60 → "Under Performance Review"
//
// 3️⃣ Modify the seniority switch:
//     - Add a special case for years >= 20 → "Diamond Loyalty Award"
// -------------------------------------------------------------------------
