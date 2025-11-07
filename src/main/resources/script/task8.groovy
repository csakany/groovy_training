// This demonstration summarizes time-sheet entries by calculating totals and averages for HR attendance reviews.
// It combines the loop patterns from slide 8 with list processing ideas from slides 17-18 and message handling from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def hourText = message.getProperty("hourLogs") ?: ""
    // split(",") and collect { it.trim() } clean the CSV, while collect { it as Integer } casts each entry to an Integer.
    def hours = hourText.split(",").collect { it.trim() }.findAll { it }.collect { it as Integer }
    // sum() totals the list contents, and the Elvis operator (?:) returns 0 if the list was empty.
    def total = hours.sum() ?: 0
    // size() counts elements so we can compute an average only when the list contains data.
    def average = hours ? total / hours.size() : 0
    // String.format('%.1f', average) formats the number with one decimal place for the report.
    def report = """Recorded Hours: ${hours}\nTotal Hours: ${total}\nAverage Per Day: ${String.format('%.1f', average)}"""
    // setBody outputs the report payload, and setProperty exposes summary metrics to downstream steps.
    message.setBody(report)
    message.setProperty("totalHours", total)
    message.setProperty("averageHours", String.format('%.1f', average))
    return message
}

/*
Practice Task 8:
1. Read daily hour values from the hourLogs property.
2. Convert them into integers with collect and related helpers.
3. Calculate the total and average hours using the same list operations.
4. Present the results in the body and store the summary numbers as properties.
*/
