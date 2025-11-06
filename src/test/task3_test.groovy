import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task3.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

def body = new File('../../data/in/ecMaritalStatus.xml')
exchange.getIn().setBody(body)
msg.setBody(exchange.getIn().getBody())

msg.setProperty('externalCode', 'D')
msg.setProperty('locales', 'en_GB,fr_FR')

script.processData(msg)
List<Map> labels = msg.getBody(List)

assert labels.size() == 2
assert labels[0].locale == 'en_GB'
assert labels[0].label.toLowerCase().contains('divorc')
assert labels[1].locale == 'fr_FR'
assert msg.getProperty('localeCount') == 2
assert msg.getProperty('defaultLabel').toLowerCase().contains('divorc')
