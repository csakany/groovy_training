package src.main.resources.script

import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

/**
 * Task 4 demonstration script – Hierarchical XML transformation.
 *
 * Scenario: HR needs a management directory that lists each parent position
 * together with the titles of the roles that report into it. The script reads
 * the SuccessFactors position hierarchy and prepares a digestible structure
 * for further reporting.
 */
def Message processData(Message message) {
    Object body = message.getBody(Object)
    if (!body) {
        message.setBody([])
        message.setProperty("managerCount", 0)
        return message
    }

    def slurper = new XmlSlurper()
    def root = (body instanceof File) ? slurper.parse(body) : slurper.parseText(body.toString())

    def positionNodes = root.Position ?: root.children()
    Map<String, List<Map>> hierarchy = [:].withDefault { [] }

    positionNodes.each { pos ->
        String parentCode = pos.parentPosition?.Position?.code?.text()
        String parentTitle = pos.parentPosition?.Position?.externalName_defaultValue?.text()
        if (!parentCode) {
            return
        }
        Map<String, String> childInfo = [
            code : pos.code.text(),
            title: pos.externalName_defaultValue.text(),
            parentTitle: parentTitle
        ]
        hierarchy[parentCode] << childInfo
    }

    List<Map> summary = hierarchy.collect { String parentCode, List<Map> reports ->
        String parentTitle = reports.find { it.parentTitle }?.parentTitle ?: 'Unknown'
        [
            parentPositionCode: parentCode,
            parentTitle       : parentTitle,
            directReports     : reports
                .collect { [code: it.code, title: it.title] }
                .sort { it.title }
        ]
    }.sort { it.parentTitle }

    message.setBody(summary)
    message.setProperty("managerCount", summary.size())

    return message
}
