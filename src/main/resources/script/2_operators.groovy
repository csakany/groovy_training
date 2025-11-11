// Operators and math functions in Groovy

def a = 10
def b = 3
println "a = $a [${a.getClass().simpleName}]"
println "b = $b [${b.getClass().simpleName}]"

// Addition
def sum = a + b
println "\nAddition: a + b = $sum [${sum.getClass().simpleName}]"

// Subtraction
def diff = a - b
println "Subtraction: a - b = $diff [${diff.getClass().simpleName}]"

// Multiplication
def prod = a * b
println "Multiplication: a * b = $prod [${prod.getClass().simpleName}]"

// Division (normal division gives BigDecimal in Groovy)
def div = a / b
println "Division: a / b = $div [${div.getClass().simpleName}]"

// Integer division (truncates decimals)
def intDiv = a.intdiv(b)
println "Integer division (intdiv): a.intdiv(b) = $intDiv [${intDiv.getClass().simpleName}]"

// Modulus (remainder)
def mod = a % b
println "Modulus: a % b = $mod [${mod.getClass().simpleName}]"

println "\n===== ROUNDING ====="

def rawValue = 10.56789
def rounded = Math.round(rawValue * 100) / 100  // round to 2 decimals
println "Original: $rawValue"
println "Rounded to 2 decimals: $rounded [${rounded.getClass().simpleName}]"

println "\n===== POWER & ROOT ====="

def base = 4
def exp = 3
def power = base ** exp
println "Power: $base ** $exp = $power [${power.getClass().simpleName}]"

// Square root
def num = 81
def sqrtVal = Math.sqrt(num)
println "Square root: sqrt($num) = $sqrtVal [${sqrtVal.getClass().simpleName}]"