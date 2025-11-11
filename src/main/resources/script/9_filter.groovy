// Filtering with closures, reusable predicates, and findAll / grep


// Sample dataset
def employees = [
        [name: "Thomas Müller", department: "Vertrieb", salary: 5200, years: 6,  performance: 91],
        [name: "Anna Schmidt",  department: "IT",       salary: 6100, years: 4,  performance: 86],
        [name: "Peter Weber",   department: "HR",       salary: 4300, years: 8,  performance: 72],
        [name: "Sarah Lee",     department: "Sales",    salary: 5600, years: 2,  performance: 88],
        [name: "John Becker",   department: "Finance",  salary: 5900, years: 10, performance: 79],
        [name: "Alex Kim",      department: "Sales",    salary: 4800, years: 1,  performance: 93],
        [name: "Julia Braun",   department: "IT",       salary: 6500, years: 7,  performance: 95]
]

// 1️⃣ Inline closure filtering with findAll
def highPerformers = employees.findAll { it.performance >= 85 }
println "\n1) High performers (>=85):"
highPerformers.each { println " - ${it.name} (${it.performance})" }

// 2️⃣ Reusable closure variable
def isSalesDept = { e -> e.department in ["Sales", "Vertrieb"] }
def salesPeople = employees.findAll(isSalesDept)
println "\n2) Sales/Vertrieb employees:"
salesPeople.each { println " - ${it.name} (${it.department})" }

// 3️⃣ Separate method predicate
boolean isSenior(emp) { emp.years >= 5 }
def seniors = employees.findAll(this.&isSenior)
println "\n3) Seniors (years >= 5):"
seniors.each { println " - ${it.name} (${it.years} yrs)" }

// 4️⃣ Combined filters (closure + method)
def topSeniorSales = employees
        .findAll(isSalesDept)
        .findAll(this.&isSenior)
        .findAll { it.performance >= 90 }

println "\n4) Top senior salespeople (dept + years + performance):"
topSeniorSales.each { println " - ${it.name}" }

// 5️⃣ Negation (exclude HR)
def nonHr = employees.findAll { it.department != "HR" }
println "\n5) Non-HR staff:"
nonHr.each { println " - ${it.name} (${it.department})" }

// 6️⃣ Post-filter transformation with collect
def itHighPerfNames = employees
        .findAll { it.department == "IT" && it.performance >= 90 }
        .collect { it.name }

println "\n7) IT high performers (names only): $itHighPerfNames"

// 7️⃣ Composable predicates using closures
def hasPerf   = { min -> { e -> e.performance >= min } }
def hasYears  = { min -> { e -> e.years >= min } }
def inDepts   = { depts -> { e -> e.department in depts } }

def filtered = employees
        .findAll(inDepts(["IT", "Finance"]))
        .findAll(hasPerf(80))
        .findAll(hasYears(3))

println "\n8) Composed filters (IT/Finance + perf>=80 + years>=3):"
filtered.each { println " - ${it.name} (${it.department}, perf=${it.performance}, yrs=${it.years})" }


// -------------------------------------------------------------------------
// 💡 PRACTICE TASKS
//
// 1️⃣ Create a reusable closure earnsAtLeast(amount) that filters by salary >= amount
// 2️⃣ Create a method predicate isTopTalent(emp):
//     performance >= 90 OR (performance >= 85 AND years >= 5)
// 3️⃣ Chain filters to get:
//     Sales/Vertrieb AND performance >= 85 AND NOT senior (<5 years)
//     Then map results to "Name (perf/yrs)" strings via collect().
// -------------------------------------------------------------------------