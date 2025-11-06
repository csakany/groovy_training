import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task24.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
def xmlContent = '''<Employees>
    <Employee status="active"><Name>Avery</Name></Employee>
    <Employee status="inactive"><Name>Chris</Name></Employee>
    <Employee status="onLeave"><Name>Taylor</Name></Employee>
</Employees>'''
msg.setBody(xmlContent)
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
