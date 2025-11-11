import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/CPI3_XML_filter.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)


def xmlContent = new File('../../data/in/CE_response.xml').text
msg.setBody(xmlContent)

msg.setProperty("person_id_external", "101020,101032")


script.processData(msg)

exchange.getIn().setBody(msg.getBody())

println("Body:\n${msg.getBody()}")

println('Properties:')
msg.getProperties().each { k, v -> println("  $k = $v") }

println "\nHeaders:"
msg.getHeaders().each { k, v -> println "  $k = $v" }