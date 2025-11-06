// This demonstration reads value-mapping entries by calling the CPI ValueMapping API from a Groovy script.
// It reflects the integration patterns from slides 56-57 alongside the message handling reminders on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    def valueMappingApi = message.getProperty("valueMappingApi")
    String countryCode = message.getProperty("countryCode") ?: "US"
    String region = valueMappingApi?.getMappedValue("HR", "CountryCode", countryCode, "Region") ?: "Unknown"

    message.setBody("Country ${countryCode} maps to region ${region}")
    message.setProperty("mappedRegion", region)
    return message
}
