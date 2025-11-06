import com.sap.gateway.ip.core.customdev.util.Message
import org.apache.camel.CamelContext
import org.apache.camel.Exchange
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.support.DefaultExchange

import static groovy.test.GroovyAssert.shouldFail

GroovyShell shell = new GroovyShell()
Script script = shell.parse(new File('../../src/main/resources/script/task5_todo.groovy'))

CamelContext context = new DefaultCamelContext()
Exchange exchange = new DefaultExchange(context)
Message msg = new Message(exchange)

msg.setBody(new File('../../data/in/CE_response.xml'))

Exception error = shouldFail(UnsupportedOperationException) {
    script.processData(msg)
}
assert error.message.contains('TODO')
