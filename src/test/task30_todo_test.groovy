import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task30_todo.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
def employeeXml = '<Employee departmentCode="HR"><Name>Avery</Name></Employee>'
def directoryXml = '<Departments><Department code="HR"><Name>Human Resources</Name></Department></Departments>'
msg.setBody(employeeXml)
msg.setProperty("departmentDirectory", directoryXml)
msg.setProperty("delayMillis", "5")
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
