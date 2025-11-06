package src.main.resources.script

import com.sap.gateway.ip.core.customdev.util.Message

/**
 * Task 2 demonstration script – Working with collections and closures.
 *
 * Scenario: HR analytics wants a quick view of participation in development
 * programmes. Each training record contains the employee, course name and
 * learning hours. The script groups the records by course and calculates
 * total learning hours together with a list of participants.
 */
def Message processData(Message message) {
    List<Map> sessions = (message.getProperty("trainingSessions") ?: []) as List<Map>
    if (!sessions) {
        message.setBody([:])
        message.setProperty("trainingCourseCount", 0)
        return message
    }

    List<Map> normalised = sessions.collect { Map record ->
        [
            employeeId   : record.employeeId?.toString(),
            employeeName : record.employeeName?.toString(),
            course       : record.course?.toString(),
            hours        : (record.hours ?: 0) as BigDecimal
        ]
    }

    Map<String, Map> summary = normalised
        .findAll { it.course }
        .groupBy { it.course }
        .collectEntries { String course, List<Map> courseRecords ->
            BigDecimal totalHours = courseRecords.collect { it.hours ?: 0 }.sum() as BigDecimal
            List<String> participants = courseRecords
                .collect { it.employeeName }
                .findAll { it }
                .unique()
                .sort()
            [(course): [totalHours: totalHours, participants: participants]]
        }

    message.setBody(summary)
    message.setProperty("trainingCourseCount", summary.size())

    return message
}
