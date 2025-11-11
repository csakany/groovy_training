
// ----------------------------------------------------------
// 1️⃣ EMAIL VALIDATION
// ----------------------------------------------------------

// Test string
def email = "john.doe@example.com"

// Regex pattern:
// ^ and $ → ensure we match the *whole* string
// [A-Za-z0-9._%+-]+ → username part (letters, digits, allowed symbols)
// @ → literal @ symbol
// [A-Za-z0-9.-]+ → domain name part
// \.[A-Za-z]{2,} → top-level domain (e.g., .com, .hu)
def emailPattern = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/

println "1️⃣ Email Validation"
println "Email: ${email}"
println "Valid? " + (email ==~ emailPattern)
println "Explanation: Checks full email format including '@' and domain.\n"


// ----------------------------------------------------------
// 2️⃣ PHONE NUMBER VALIDATION (Hungarian format)
// ----------------------------------------------------------

// Example phone number
def phone = "+36 70 123 4567"

// Regex pattern:
// ^ and $ → full string match
// (\+36|06) → must start with +36 or 06
// \s? → optional space
// \d{1,2} → area code (1–2 digits)
// \s? → optional space
// \d{3} → first 3 digits
// \s? → optional space
// \d{3,4} → last 3–4 digits
def phonePattern = /^(\+36|06)\s?\d{1,2}\s?\d{3}\s?\d{3,4}$/

println "2️⃣ Phone Validation"
println "Phone: ${phone}"
println "Valid? " + (phone ==~ phonePattern)
println "Explanation: Accepts Hungarian phone numbers (+36 or 06) with optional spaces.\n"


// ----------------------------------------------------------
// 3️⃣ EMPLOYEE ID VALIDATION
// ----------------------------------------------------------

// Example employee ID
def empId = "EMP-2024-0123"

// Regex pattern:
// ^EMP- → fixed prefix
// \d{4} → 4-digit year
// - → literal dash
// \d{3,5} → 3–5 digit unique ID
// $ → end of string
def empIdPattern = /^EMP-\d{4}-\d{3,5}$/

println "3️⃣ Employee ID Validation"
println "Employee ID: ${empId}"
println "Valid? " + (empId ==~ empIdPattern)
println "Explanation: Ensures consistent internal ID format (e.g., EMP-2024-0123).\n"


// ----------------------------------------------------------
// 4️⃣ EXTRACT MULTIPLE EMAILS FROM A TEXT
// ----------------------------------------------------------

// Example text
def text = "Please contact anna.kovacs@synergy.hu or peter.nagy@hr.example.com for info."

// Pattern similar to email, but without ^ and $ (so we can find many matches)
def foundEmails = (text =~ /[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}/)

println "4️⃣ Extract Emails"
println "Text: ${text}"
println "Found emails:"
foundEmails.each { println " - $it" }
println "Explanation: Finds all substrings that match an email pattern.\n"


// ----------------------------------------------------------
// 5️⃣ REDACT (REPLACE) SENSITIVE INFORMATION
// ----------------------------------------------------------

// Example confidential line
def confidential = "Employee John Doe, phone: +36 70 555 0101"

// Regex pattern:
// \+36 → literal +36
// \s? → optional space
// \d{2} → 2-digit area code
// \s? → optional space
// \d{3} → next 3 digits
// \s? → optional space
// \d{3,4} → final digits (3–4 digits)
def redacted = confidential.replaceAll(/\+36\s?\d{2}\s?\d{3}\s?\d{3,4}/, "[REDACTED PHONE]")

println "5️⃣ Redacting Sensitive Info"
println "Before: ${confidential}"
println "After : ${redacted}"
println "Explanation: Uses replaceAll() to anonymize phone numbers.\n"

