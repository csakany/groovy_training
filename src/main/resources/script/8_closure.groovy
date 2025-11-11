// Groovy Closures

// 1️⃣ Define a simple closure (no parameters)
def sayHello = { println "Welcome to the HR system!" }
sayHello() // Call the closure

// 2️⃣ Closure with one implicit parameter (it)
def greet = { println "Hello, $it!" }
greet("Thomas Müller")

// 3️⃣ Closure with explicit parameters
def calculateBonus = { baseSalary, performance ->
    def rate = (performance == "excellent") ? 0.20 :
            (performance == "good") ? 0.15 : 0.10
    return baseSalary * rate
}

println "\nBonus for excellent performance: \$${calculateBonus(5000, 'excellent')}"
println "Bonus for good performance: \$${calculateBonus(5000, 'good')}"

// 4️⃣ Closure that operates on a list (functional style)
def salaries = [3000, 4000, 5000, 6000]
def adjusted = salaries.collect { it * 1.05 } // 5% raise
println "\nAdjusted salaries (via collect closure): $adjusted"

// 5️⃣ Closure that takes another closure as a parameter
def processEmployee = { name, salary, logic ->
    def result = logic(salary)
    println "Processed: ${name}, result = \$${String.format('%.2f', result)}"
}

// Passing different closures to the same method
def taxClosure = { salary -> salary * 0.25 }
def bonusClosure = { salary -> salary * 0.10 }

println "\nUsing processEmployee with taxClosure:"
processEmployee("Anna Schmidt", 5000, taxClosure)

println "Using processEmployee with bonusClosure:"
processEmployee("Anna Schmidt", 5000, bonusClosure)

// 6️⃣ Returning a closure from a method (closure factory)
def createMultiplier = { factor ->
    return { value -> value * factor }
}

def doubleIt = createMultiplier(2)
def tripleIt = createMultiplier(3)

println "\nClosure returned from a closure:"
println "Double 1000 = ${doubleIt(1000)}"
println "Triple 1000 = ${tripleIt(1000)}"

println "\n===== END OF DEMO ====="

// -------------------------------------------------------------------------
// 💡 TASK:
//
// 1️⃣ Create a closure named `evaluatePerformance` that takes a score (0–100)
//     and returns "Excellent", "Good", "Satisfactory", or "Needs Improvement".
//
// 2️⃣ Use it with a list of sample scores and the collect() method.
//
// Example idea:
// def evaluatePerformance = { score ->
//     if (score >= 90) return "Excellent"
//     else if (score >= 75) return "Good"
//     else if (score >= 60) return "Satisfactory"
//     else return "Needs Improvement"
// }
// def scores = [95, 82, 68, 55]
// println scores.collect(evaluatePerformance)
// -------------------------------------------------------------------------
