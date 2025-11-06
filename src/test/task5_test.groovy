import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task5.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

msg.setBody(new File('../../data/in/CE_response.xml'))
msg.setProperty('jobKeyword', 'Human Resources')

script.processData(msg)
List<Map> leaders = msg.getBody(List)

assert leaders.size() > 0
assert msg.getProperty('hrEmployeeCount') == leaders.size()
assert msg.getProperty('primaryContactEmail')

def firstLeader = leaders.find { it.personId == '101032' }
assert firstLeader
assert firstLeader.jobTitle.toLowerCase().contains('human resources')
assert firstLeader.contactChannels.email == firstLeader.email
