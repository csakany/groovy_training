// Demonstration of Groovy data types

// Integer
int age = 35
println "Integer example (age): $age"
println "Type: ${age.getClass().simpleName}"

// Long
long population = 7_900_000_000L
println "Long example (world population): $population"

// Double
double pi = 3.14159
println "Double example (pi): $pi"

// BigDecimal
BigDecimal price = new BigDecimal("19.99")
BigDecimal tax = new BigDecimal("0.27")
BigDecimal total = price + (price * tax)
println "BigDecimal example (price + tax): $total"

// String
String name = "Groovy"
println "String example: Hello, $name!"

// Boolean
boolean isActive = true
println "Boolean example: isActive = $isActive"

// List (Array)
def fruits = ["Banana", "Cherry"]
fruits.add("Apple")
fruits << "Lime"
fruits << "Lime"
fruits.remove("Lime")
println "List example: $fruits"
println "Access element: ${fruits[1]}"

// Map
def person = ["name": "John", "age": 28, "city": "Budapest"]
person.put("gender", "m")
person.putAll([zip: "9024", street: "Main str. 14"])

println "Map example: $person"
println person["name"]
println "Access key 'city': ${person.city}"
println  person["age"].getClass().simpleName

// Range
def range = 1..5
println "Range example: $range"


// Dynamic typing
def dynamic = "I am a String"
println "Dynamic type example: $dynamic"
dynamic = 42
println "Now dynamic is an Integer: $dynamic"

// Null
def nothing = null
println "Null example: $nothing"
println "Null Type: ${nothing.getClass().simpleName}"


// Array of mixed types
def mixed = [123, "Text", 4.56, true]
println "Mixed-type list: $mixed"
println mixed[0]
println mixed[-1]

