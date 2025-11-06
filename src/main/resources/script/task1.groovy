package src.main.resources.script
import com.sap.gateway.ip.core.customdev.util.Message
import java.time.LocalDate
import java.time.format.DateTimeFormatter

def Message processData(Message message) {

    //1. task, A part


    // Requesting Property
    def userIds = message.getProperty("userIds")

    // Check if the Property exists, if not, return with error message
    if (!userIds) {
        message.setProperty("filterString", "userId Property does not exist!")
        return message
    }

    // Remove spaces if there is any
    def cleanIds = userIds.replace(" ", "")

    // Replace commas and put apostrophes next to each userId, and at the beginning and at the end of the whole string
    def quotedIds = "'" + cleanIds.replace(",", "','") + "'"

    // Complete the Odata filter with the just created string of userIds, and return the message as a Property
    def filterString = "\$filter=userId in ${quotedIds}"

    message.setProperty("filterString",filterString)



    //1. task, B part

    //Query the real world date
    LocalDate today = LocalDate.now()
    // Your formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    // Example input date
    String inputDate = today.format(formatter)
    LocalDate date = LocalDate.parse(inputDate, formatter)
    // First day of month = same year & month, day = 1
    LocalDate firstDayOfMonth = date.withDayOfMonth(1)
    // Last day of month = use lengthOfMonth()
    LocalDate lastDayOfMonth = date.withDayOfMonth(date.lengthOfMonth())
    // Format results
    String firstDayStr = firstDayOfMonth.format(formatter)
    String lastDayStr  = lastDayOfMonth.format(formatter)
    println ("First day of month: $firstDayStr")
    println ("Last day of month:  $lastDayStr")


    //1. task, C part


    return message

}