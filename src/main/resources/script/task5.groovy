package src.main.resources.script

import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlSlurper

/**
 * Task 5 demonstration script – Consolidating HR master data.
 *
 * Scenario: The HR business partner needs a people profile for senior leaders
 * working in Human Resources roles. The script parses the Compound Employee
 * response, finds the most recent job information segment, and returns a
 * structured summary for downstream workflows.
 */
def Message processData(Message message) {
    Object body = message.getBody(Object)
    if (!body) {
        message.setBody([])
        message.setProperty("hrEmployeeCount", 0)
        return message
    }

    def slurper = new XmlSlurper()
    def root = (body instanceof File) ? slurper.parse(body) : slurper.parseText(body.toString())

    String keyword = (message.getProperty("jobKeyword") ?: 'Human Resources').toString().toLowerCase()

    List<Map> employees = root.CompoundEmployee.collect { ce ->
        def person = ce.person
        def personalInfo = person.personal_information
        def employment = person.employment_information
        def jobSegments = employment.'job_information'
        def sortedSegments = jobSegments.sort { it.start_date.text() }
        def currentSegment = sortedSegments ? sortedSegments.last() : null

        [
            personId     : person.person_id_external.text(),
            name         : personalInfo.formal_name.text() ?: "${personalInfo.first_name.text()} ${personalInfo.last_name.text()}".trim(),
            jobTitle     : currentSegment?.job_title?.text(),
            department   : currentSegment?.department?.text(),
            managerId    : currentSegment?.manager_id?.text(),
            businessUnit : currentSegment?.business_unit?.text(),
            workLocation : currentSegment?.location?.text(),
            startDate    : currentSegment?.start_date?.text(),
            email        : person.email_information.email_address.text(),
            phone        : person.phone_information ? "${person.phone_information.country_code.text()}-${person.phone_information.phone_number.text()}" : null,
            directReports: employment.direct_reports.text() ? employment.direct_reports.text().toInteger() : 0
        ]
    }

    List<Map> hrLeaders = employees.findAll { Map record ->
        record.jobTitle?.toLowerCase()?.contains(keyword)
    }.collect { Map record ->
        record + [contactChannels: [
            email: record.email,
            phone: record.phone
        ]]
    }.sort { it.name }

    message.setBody(hrLeaders)
    message.setProperty("hrEmployeeCount", hrLeaders.size())
    if (hrLeaders) {
        message.setProperty("primaryContactEmail", hrLeaders.first().email)
    }

    return message
}
