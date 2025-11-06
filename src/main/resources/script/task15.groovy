import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def headcount = [HR: 4, Finance: 6, IT: 8]
    headcount.HR = headcount.HR + 1
    headcount['Learning'] = 3
    def reportLines = headcount.collect { dept, count -> "${dept}: ${count}" }
    message.setBody(reportLines.join("\n"))
    message.setProperty("totalDepartments", headcount.size())
    message.setProperty("hrHeadcount", headcount.HR)
    return message
}
