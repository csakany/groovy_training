import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def hourText = message.getProperty("hourLogs") ?: ""
    def hours = hourText.split(",").collect { it.trim() }.findAll { it }.collect { it as Integer }
    def total = hours.sum() ?: 0
    def average = hours ? total / hours.size() : 0
    def report = """Recorded Hours: ${hours}\nTotal Hours: ${total}\nAverage Per Day: ${String.format('%.1f', average)}"""
    message.setBody(report)
    message.setProperty("totalHours", total)
    message.setProperty("averageHours", String.format('%.1f', average))
    return message
}
