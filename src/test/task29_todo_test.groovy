import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

class StubValueMappingApi {
    Map mappings
    String getMappedValue(String agency, String sourceScheme, String sourceValue, String targetScheme) {
        mappings.get("${sourceScheme}:${sourceValue}")
    }
}

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task29_todo.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
msg.setBody("")
msg.setProperty("countryCode", "US")
msg.setProperty("valueMappingApi", new StubValueMappingApi(mappings: ["CountryCode:US": "North America"]))
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
