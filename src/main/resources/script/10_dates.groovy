// Working with Date and SimpleDateFormat in Groovy

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

println "\n===== DATE HANDLING DEMO (SimpleDateFormat + Date) ====="

// 1️⃣ Convert String to Date
def dateStr1 = "2025-11-11"
def dateStr2 = "11/11/2025 14:35"

// Define input formatters
def fmtIso = new SimpleDateFormat("yyyy-MM-dd")
def fmtCustom = new SimpleDateFormat("MM/dd/yyyy HH:mm")

Date date1 = fmtIso.parse(dateStr1)
Date date2 = fmtCustom.parse(dateStr2)

println "Parsed Date (ISO):     $date1"
println "Parsed Date (Custom):  $date2"

// 2️⃣ Add days to a date
Calendar cal = Calendar.getInstance()
cal.setTime(date1)
cal.add(Calendar.DAY_OF_MONTH, 10)
Date plus10 = cal.getTime()

cal.setTime(date1)
cal.add(Calendar.DAY_OF_MONTH, -3)
Date minus3 = cal.getTime()

println "\nAdd/Subtract days:"
println " - $date1 + 10d = $plus10"
println " - $date1 -  3d = $minus3"

// 3️⃣ First day of the current month
Calendar now = Calendar.getInstance()
now.set(Calendar.DAY_OF_MONTH, 1)
Date firstOfMonth = now.getTime()
println "\nFirst day of current month: $firstOfMonth"

// 4️⃣ Days between two dates
def start = fmtIso.parse("2025-11-01")
def end = fmtIso.parse("2025-12-05")
long diffMs = end.time - start.time
long diffDays = diffMs / (1000 * 60 * 60 * 24)
println "\nDays between:"
println " - From $start to $end -> $diffDays days"

// 5️⃣ Convert Date to String (formatting)
def fmtHu = new SimpleDateFormat("yyyy.MM.dd")
def fmtLong = new SimpleDateFormat("EEEE, MMMM d, yyyy HH:mm")

println "\nFormatting:"
println " - yyyy.MM.dd:  ${fmtHu.format(date1)}"
println " - Long EN:     ${fmtLong.format(date2)}"

// 6️⃣ Current date/time formatted
def fmtFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
def nowFormatted = fmtFull.format(new Date())
println "\nCurrent timestamp formatted: $nowFormatted"

// 7️⃣ Flexible parsing with multiple formats
def parseFlexible = { String s ->
    def formats = [
            new SimpleDateFormat("yyyy-MM-dd"),
            new SimpleDateFormat("dd.MM.yyyy"),
            new SimpleDateFormat("MM/dd/yyyy")
    ]
    for (f in formats) {
        try {
            return f.parse(s)
        } catch (ignored) {}
    }
    throw new IllegalArgumentException("Unsupported date format: $s")
}

println "\nFlexible parsing:"
println " - '11.11.2025' -> ${parseFlexible('11.11.2025')}"
println " - '12/05/2025' -> ${parseFlexible('12/05/2025')}"
