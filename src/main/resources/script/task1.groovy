package src.main.resources.script

import com.sap.gateway.ip.core.customdev.util.Message

/**
 * Task 1 demonstration script – String handling and message properties.
 *
 * Scenario: HR needs a quick welcome note for each new hire. The script
 * reads message properties that contain onboarding information and builds
 * a friendly summary that can be sent as part of an onboarding email.
 */
def Message processData(Message message) {
    String firstName = (message.getProperty("firstName") ?: "Colleague").toString()
    String lastName = (message.getProperty("lastName") ?: "").toString()
    String jobTitle = (message.getProperty("jobTitle") ?: "our team").toString()
    String startDate = message.getProperty("startDate")?.toString()
    String hrContact = (message.getProperty("hrContact") ?: "the HR team").toString()

    List<String> sections = []
    sections << "Welcome ${firstName} ${lastName}".trim()
    sections << "We are excited to have you join us as ${jobTitle}."
    if (startDate) {
        sections << "Your official start date is ${startDate}."
    }
    sections << "If you have any questions, please reach out to ${hrContact}."

    String summary = sections.join(' ')
    message.setBody(summary)
    message.setProperty("welcomeSummaryLength", summary.size())

    return message
}
