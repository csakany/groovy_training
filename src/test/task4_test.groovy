import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task4.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

def body = new File('../../data/in/position.xml')
exchange.getIn().setBody(body)
msg.setBody(exchange.getIn().getBody())

script.processData(msg)
List<Map> summary = msg.getBody(List)

assert summary.size() > 0
assert msg.getProperty('managerCount') == summary.size()

def hrLeader = summary.find { it.parentPositionCode == '50014279' }
assert hrLeader
assert hrLeader.parentTitle.toLowerCase().contains('human resources')
assert hrLeader.directReports*.title.contains('Chief Learning Officer')
