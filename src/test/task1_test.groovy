import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task1.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

msg.setProperty('firstName', 'Tanya')
msg.setProperty('lastName', 'Lopez')
msg.setProperty('jobTitle', 'HR Coordinator')
msg.setProperty('startDate', '2024-07-01')
msg.setProperty('hrContact', 'Jamie from HR Operations')

script.processData(msg)
String result = msg.getBody(String)
assert result.contains('Welcome Tanya Lopez')
assert result.contains('HR Coordinator')
assert result.contains('2024-07-01')
assert result.contains('Jamie from HR Operations')
assert msg.getProperty('welcomeSummaryLength') == result.size()
