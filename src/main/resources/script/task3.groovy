package src.main.resources.script

import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

/**
 * Task 3 demonstration script – Parsing XML with XmlSlurper.
 *
 * Scenario: HR localisation teams need the translated labels for a picklist.
 * The script extracts labels for a specific marital status option, supports
 * optional locale filtering, and exposes the results in both the body and
 * properties for downstream steps.
 */
def Message processData(Message message) {
    Object body = message.getBody(Object)
    if (!body) {
        message.setBody([])
        message.setProperty("localeCount", 0)
        return message
    }

    def slurper = new XmlSlurper()
    def root = (body instanceof File) ? slurper.parse(body) : slurper.parseText(body.toString())

    String requestedCode = message.getProperty("externalCode")?.toString()
    if (!requestedCode) {
        throw new IllegalArgumentException("Property 'externalCode' is required")
    }

    def picklistNode = root.Picklist ? root.Picklist.find { it.picklistId?.text() == 'ecMaritalStatus' } : root
    def options = picklistNode?.picklistOptions?.PicklistOption ?: root.PicklistOption
    def option = options.find { it.externalCode?.text() == requestedCode }

    List<Map<String, String>> labels = []
    if (option) {
        labels = option.picklistLabels.PicklistLabel.collect { label ->
            [
                locale: label.locale.text(),
                label : label.label.text()
            ]
        }
    }

    List<String> localeFilter = (message.getProperty("locales") ?: "")
        .toString()
        .split(',')
        .collect { it.trim() }
        .findAll { it }

    if (localeFilter) {
        labels = labels.findAll { localeFilter.contains(it.locale) }
    }

    labels = labels.sort { it.locale }
    message.setBody(labels)
    message.setProperty("localeCount", labels.size())

    def englishLabel = labels.find { it.locale.startsWith('en') }
    if (englishLabel) {
        message.setProperty("defaultLabel", englishLabel.label)
    }

    return message
}
