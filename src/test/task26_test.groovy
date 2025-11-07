import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

class StubMessageLog {
    List attachments = []
    Map properties = [:]
    void addAttachmentAsString(String name, String content, String type) {
        attachments << [name: name, content: content, type: type]
    }
    void setStringProperty(String name, String value) {
        properties[name] = value
    }
}

class StubMessageLogFactory {
    StubMessageLogToDo log = new StubMessageLogToDo()
    StubMessageLogToDo getMessageLog(Message message) { log }
}

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task26.groovy'))
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
msg.setBody("Sample payload for attachment")
msg.setHeader("X-CorrelationId", "HR-123")
msg.setProperty("messageLogFactory", new StubMessageLogFactoryToDo())
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
println("Body:\n${msg.getBody()}")
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }
def log = msg.getProperty("messageLogFactory").log
println('Attachment count: ' + log.attachments.size())
println('Logged properties: ' + log.properties)
