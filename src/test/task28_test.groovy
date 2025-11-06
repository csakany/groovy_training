import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task28.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
msg.setBody("")
msg.setProperty("employees", "Alex Kim|HR|5, Sam Lee|IT|3, Priya Patel|HR|2, Jordan Smith|IT|7")
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
