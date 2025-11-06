import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task3_todo.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
msg.setBody("")
msg.setProperty("baseSalary", "48000")
msg.setProperty("bonus", "3500")
msg.setProperty("taxRate", "15")
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
