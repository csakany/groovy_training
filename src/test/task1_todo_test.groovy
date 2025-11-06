

import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

// Load Groovy Script
GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File(
        '../../src/main/resources/script/task1_todo.groovy'))
// Initialize CamelContext and exchange for the message
CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)
// Initialize the message body with the input file
//def body = new File('../../data/in/dependent/payload_empty.xml')
// Set exchange body in case Type Conversion is required
//exchange.getIn().setBody(body)
//msg.setBody(exchange.getIn().getBody())
def body = new File('../../data/in/sample.xml')
// Set exchange body in case Type Conversion is required
exchange.getIn().setBody(body)
msg.setBody(exchange.getIn().getBody())

msg.setProperty("userIds", "101001,101005,101010,101015")
msg.setProperty("queryType","ACTUAL")



// Execute script
script.processData(msg)
exchange.getIn().setBody(msg.getBody())
// Display results of script in console
//println("Body:\r\n${msg.getBody(String)}")
//println('Headers:')
//msg.getHeaders().each { k, v -> println("$k = $v") }
println('Properties:')
msg.getProperties().each { k, v -> println("$k = $v") }

// Save the processed body to sum_hours_by_emp.xml
//def outputFilePath = '../../data/out/sum_hours_by_emp.xml'

//def processedBody = exchange.getIn().getBody(String)
//Files.write(Paths.get(outputFilePath), processedBody.getBytes())