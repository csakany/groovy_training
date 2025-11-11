// if / else if / else in Groovy


// Sample employee data
def employee = [
        name      : "Anna Kovács",
        department: "Sales",
        years     : 4,
        performanceScore: 88
]

println "Employee: ${employee.name}"
println "Department: ${employee.department}"
println "Years in company: ${employee.years}"
println "Performance Score: ${employee.performanceScore}"

// Performance evaluation using if / else if / else
def rating

if (employee.performanceScore >= 90) {
    rating = "Outstanding"
} else if (employee.performanceScore >= 75) {
    rating = "Good"
} else if (employee.performanceScore >= 60) {
    rating = "Satisfactory"
} else {
    rating = "Needs Improvement"
}

println "\nPerformance Rating: $rating"

// Bonus calculation with multiple conditions
def bonus

if (employee.department == "Sales" && employee.performanceScore >= 85) {
    bonus = 15
} else if (employee.department == "IT" && employee.performanceScore >= 85) {
    bonus = 10
} else if (employee.department == "HR" && employee.performanceScore >= 80) {
    bonus = 8
} else {
    bonus = 3
}

println "Bonus: ${bonus}%"



// -------------------------------------------------------------------------
// 💡 TASK:
//
// Modify this script to include the following:
//
// 1️⃣ óAdd a new condition for department = "Finance":
//     - If performanceScore >= 82, bonus = 12
//
// 2️⃣ Add another check for exceptional long service:
//     - If years >= 10, print "Award: Loyalty Bonus"
// -------------------------------------------------------------------------
