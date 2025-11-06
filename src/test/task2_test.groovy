import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task2.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

msg.setProperty('trainingSessions', [
        [employeeId: '101032', employeeName: 'Sean Nielsen', course: 'Inclusive Leadership', hours: 3],
        [employeeId: '101045', employeeName: 'Priya Singh', course: 'Inclusive Leadership', hours: 3],
        [employeeId: '101045', employeeName: 'Priya Singh', course: 'Conflict Management', hours: 2],
        [employeeId: '101032', employeeName: 'Sean Nielsen', course: 'Conflict Management', hours: 1],
])

script.processData(msg)
Map result = msg.getBody(Map)

assert result.keySet() == ['Conflict Management', 'Inclusive Leadership'] as Set
assert result['Inclusive Leadership'].totalHours == 6
assert result['Inclusive Leadership'].participants == ['Priya Singh', 'Sean Nielsen']
assert result['Conflict Management'].totalHours == 3
assert result['Conflict Management'].participants == ['Priya Singh', 'Sean Nielsen']
assert msg.getProperty('trainingCourseCount') == 2
