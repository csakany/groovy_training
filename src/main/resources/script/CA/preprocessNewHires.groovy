package src.main.resources.script.CA

import com.sap.gateway.ip.core.customdev.util.Message
import groovy.xml.XmlParser
import groovy.xml.XmlUtil
import java.time.LocalDate

def Message processData(Message message) {
    Reader body = message.getBody(Reader)
    if (body) {
        Node bodyParsed = new XmlParser(false, false).parse(body)
        LocalDate today = LocalDate.now()
        bodyParsed.CompoundEmployee.each { Node ceNode ->
            NodeList employments = ceNode.person.employment_information
            if (!employments || employments.isEmpty()) {
                ceNode.parent().remove(ceNode)
            } else {
                employments.each { Node emplInfoNode ->
                    preprocessRecord(emplInfoNode, ceNode, today)
                }
                // if there are any employments left, remove the node
                employments = ceNode.person.employment_information
                if (!employments || employments.isEmpty()) {
                    ceNode.parent().remove(ceNode)
                }
            }
            message.setBody(XmlUtil.serialize(bodyParsed))
        }
    }
    return message
}

static void preprocessRecord(Node emplInfoNode, Node ceNode, LocalDate refDate) {
    if (!emplInfoNode) {
        return
    }
    if (emplInfoNode.action.text() != "INSERT") {
        emplInfoNode.parent().remove(emplInfoNode)
        return
    }
    // check job info nodes
    emplInfoNode.job_information.findAll{ Node n ->
        return n.action && n.action.text() != "INSERT"
    }.each { Node n ->
        n.parent().remove(n)
    }
    if (emplInfoNode.job_information.isEmpty()) {
        emplInfoNode.parent().remove(emplInfoNode)
        return
    }

    if(ceNode.person.employment_information.isEmpty() || ceNode.person.personal_information.isEmpty() /*|| ceNode.person.address_information.isEmpty()*/){
        ceNode.parent().remove(ceNode)
        return
    }
}