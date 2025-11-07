// This demonstration adjusts department headcount in a map to emphasize key-value collection skills.
// It reinforces the map techniques from slides 19-20 alongside message property usage from slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def headcount = [HR: 4, Finance: 6, IT: 8]
    // Using map.property accesses updates the HR entry directly.
    headcount.HR = headcount.HR + 1
    // Bracket notation headcount['Learning'] adds a brand-new key/value pair.
    headcount['Learning'] = 3
    // collect iterates through each entry, exposing dept and count arguments to format the summary text.
    def reportLines = headcount.collect { dept, count -> "${dept}: ${count}" }
    // join("\n") arranges each department on its own line and size() counts how many departments the map now holds.
    message.setBody(reportLines.join("\n"))
    message.setProperty("totalDepartments", headcount.size())
    message.setProperty("hrHeadcount", headcount.HR)
    return message
}

/*
Practice Task 15:
1. Create a map of departments and their headcount.
2. Update one of the existing entries and add a brand new department.
3. Print each entry on its own line and report helpful counts using the same map helpers.
*/
