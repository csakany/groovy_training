import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlParser
import groovy.xml.XmlUtil

def Message processData(Message message) {

    // 1️⃣ Parse CE response
    String ceXml = message.getBody()
    def parser = new XmlParser(false, false)
    Node ceRoot = parser.parseText(ceXml)

    // 2️⃣ Parse manager.xml
    String mgrXml = message.getProperty("manager") as String
    Node mgrRoot = parser.parseText(mgrXml)

    // 3️⃣ Loop through each job_information in CE
    for (Node ce in ceRoot.children()) {
        if (ce.name() == "CompoundEmployee") {

            for (Node person in ce.children()) {
                if (person.name() == "person") {

                    for (Node emp in person.children()) {
                        if (emp.name() == "employment_information") {

                            for (Node job in emp.children()) {
                                if (job.name() == "job_information") {

                                    // 4️⃣ Get manager_id and find matching manager in manager.xml
                                    String managerId = ""
                                    for (Node jobChild in job.children()) {
                                        if (jobChild.name() == "manager_id") {
                                            managerId = jobChild.text().trim()
                                        }
                                    }

                                    if (managerId) {
                                        for (Node user in mgrRoot.children()) {
                                            if (user.name() == "User") {
                                                String userId = ""
                                                String firstName = ""
                                                String lastName = ""

                                                for (Node uc in user.children()) {
                                                    if (uc.name() == "userId") userId = uc.text().trim()
                                                    if (uc.name() == "firstName") firstName = uc.text().trim()
                                                    if (uc.name() == "lastName") lastName = uc.text().trim()
                                                }

                                                // 5️⃣ Append managerFirstName and managerLastName if match found
                                                if (userId == managerId) {
                                                    job.appendNode("managerFirstName", firstName)
                                                    job.appendNode("managerLastName", lastName)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Set the enriched XML as new body
    message.setBody(XmlUtil.serialize(ceRoot))
    message.setHeader("Content-Type", "application/xml; charset=UTF-8")
    return message
}
