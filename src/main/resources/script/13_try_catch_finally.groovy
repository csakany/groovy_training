// try / catch / finally


// STEP 1️⃣: Basic try-catch
try {
    println "Dividing 10 / 0 ..."
    def result = 10 / 0   // This will throw ArithmeticException
    println "Result: $result"
} catch (ArithmeticException e) {
    println "⚠️ Caught an ArithmeticException: ${e.message}"
}

// STEP 2️⃣: Multiple catch blocks for different errors
try {
    def text = null
    println "Length of text: ${text.length()}"   // NullPointerException
} catch (NullPointerException e) {
    println "⚠️ Caught NullPointerException: ${e.message}"
} catch (Exception e) {
    println "⚠️ Caught a general Exception: ${e.message}"
}


// STEP 3️⃣: Using finally block
println "\nSTEP 3: Demonstrate finally (cleanup actions)"
def file = null
try {
    println "Opening a fake file..."
    // Simulate a problem
    throw new IOException("File not found.")
} catch (IOException e) {
    println "⚠️ Error: ${e.message}"
} finally {
    println "✅ finally block executed — closing file, releasing resources."
}


// STEP 4️⃣: Combining try-catch-finally with a return statement
println "\nSTEP 4: Return values from try/catch/finally"

def divide(a, b) {
    try {
        return a / b
    } catch (ArithmeticException e) {
        println "⚠️ Cannot divide by zero!"
        return 0
    } finally {
        println "finally block always executes (clean-up or logging)."
    }
}

println "Result of divide(10, 2): ${divide(10, 2)}"
println "Result of divide(10, 0): ${divide(10, 0)}"


// STEP 5️⃣: Nested try-catch
println "\nSTEP 5: Nested try-catch example"

try {
    println "Starting outer block..."
    try {
        def data = [1, 2, 3]
        println "Accessing 4th element: ${data[3]}"   // IndexOutOfBoundsException
    } catch (IndexOutOfBoundsException e) {
        println "⚠️ Inner catch: ${e.message}"
    }
    println "Outer block continues..."
} catch (Exception e) {
    println "Outer catch: ${e.message}"
} finally {
    println "Outer finally always runs."
}



// -------------------------------------------------------------------------
// 💡 PRACTICE TASKS:
//
// 1️⃣ Modify the divide() function to throw a custom exception
//     (e.g. throw new IllegalArgumentException("Invalid numbers")).
//
// 2️⃣ Add a try-catch that parses a date string:
//     Use SimpleDateFormat and handle ParseException.
//
// 3️⃣ Create a method readFile(path) that throws FileNotFoundException,
//     and handle it gracefully with a finally block that prints "Done".
// -------------------------------------------------------------------------
