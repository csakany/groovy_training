// This demonstration reads value-mapping entries by calling the CPI ValueMapping API from a Groovy script.
// It reflects the integration patterns from slides 56-57 alongside the message handling reminders on slide 47.
import com.sap.gateway.ip.core.customdev.util.Message

def Message processData(Message message) {
    // valueMappingApi is supplied by the runtime; using ?. safely calls getMappedValue only when it exists.
    def valueMappingApi = message.getProperty("valueMappingApi")
    String countryCode = message.getProperty("countryCode") ?: "US"
    // getMappedValue(domain, sourceType, sourceValue, targetType) looks up the region tied to the incoming code.
    String region = valueMappingApi?.getMappedValue("HR", "CountryCode", countryCode, "Region") ?: "Unknown"

    // setBody surfaces the mapping result and setProperty shares the looked-up region with downstream steps.
    message.setBody("Country ${countryCode} maps to region ${region}")
    message.setProperty("mappedRegion", region)
    return message
}

/*
Practice Task 29:
- Retrieve the ValueMapping API from message properties, request a mapped value such as CountryCode -> Region, and present the result.
- Store the looked-up value as a property, mirroring the integration shown above.
*/
