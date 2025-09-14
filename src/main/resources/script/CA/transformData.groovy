package src.main.resources.script.CA

import com.sap.gateway.ip.core.customdev.util.Message
//import com.sap.it.api.ITApiFactory
//import com.sap.it.api.mapping.ValueMappingApi
import groovy.xml.MarkupBuilder
import groovy.xml.XmlParser

import java.time.format.DateTimeFormatter

def Message processData(Message message) {
    //def vmapService = ITApiFactory.getApi(ValueMappingApi.class, null)
    Reader body = message.getBody(Reader)
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    DateTimeFormatter sageDateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    Date today = new Date()
    tempToday = today.format("yyyy-MM-dd")
    today = Date.parse( "yyyy-MM-dd", tempToday )

    if (body) {
        Node bodyParsed = new XmlParser(false, false).parse(body)
        List<String> fieldsToCheck = [
                "hireDate"
        ]

        List<Record> recordsToMap = new ArrayList<>()
        List<Record> recordsToMapMLE32HRAN = new ArrayList<>()

        bodyParsed.CompoundEmployee.each { Node ceRecord ->
            NodeList addressInfoNodes = ceRecord.person.address_information
            Node addressInfoNode = getRelevantNode(addressInfoNodes, today)

            NodeList emailInfoNodes = ceRecord.person.email_information
            Node emailInfoNode = getRelevantNode(emailInfoNodes, today)

            NodeList phoneInfoNodes = ceRecord.person.phone_information
            Node phoneInfoNode = getRelevantNode(phoneInfoNodes, today)

            NodeList personalInfoNodes = ceRecord.person.personal_information
            Node personalInfoNode = getRelevantNode(personalInfoNodes, today)

            //Find nationalInfo where type eq CPR
            Node nationInfoNode = ceRecord.person.national_id_card.find {
                it.card_type.text() == "CPR"
            }

            String personIdExternal = ceRecord.person.person_id_external.text()
            ceRecord.person.employment_information.each { Node employmentInfo ->
                String startDate = employmentInfo.start_date.text()
                Date start_date = Date.parse( "yyyy-MM-dd", startDate )
                startDate = start_date.format("ddMMyyyy")

                String seniorityDate = employmentInfo.seniorityDate.text()
                if (seniorityDate) {
                    Date seniority_date = Date.parse( "yyyy-MM-dd", seniorityDate )
                    seniorityDate = seniority_date.format("yyMMdd")
                }

                Node jobInfoRecord = getRelevantNode(employmentInfo.job_information, today)

                //apply value mapping

                /* String title = vmapService.getMappedValue(
                     "SF", "salutation", personalInfoNode.salutation.text(), "SDWorkx", "Salutation"
                 )*/


                //Employee record MLE27HRME
                Employee employeeRecord = new Employee()

                employeeRecord.setCustomerId("894")
                employeeRecord.setEmployer("1")
                employeeRecord.setIdentification("MLE-27-HRME")
                employeeRecord.setEmployee(employmentInfo.custom_string1.text())
                employeeRecord.setPersonIdExternal(personIdExternal)

                if (nationInfoNode) {
                    employeeRecord.setCprNo(nationInfoNode.national_id.text())
                }

                employeeRecord.setCvrNo()

                if (personalInfoNode) {
                    employeeRecord.setLastName(personalInfoNode.last_name_alt1.text())
                    employeeRecord.setFirstName(personalInfoNode.first_name_alt1.text())
                }

                employeeRecord.setAddress1("X")
                employeeRecord.setAddress2()
                employeeRecord.setPostalCode("1001")
                employeeRecord.setForeignPostalDistrict()
                employeeRecord.setFirstTimeEmployment(startDate)
                if (emailInfoNode) {
                    employeeRecord.setEmail(emailInfoNode.email_address.text())
                }
                employeeRecord.setCountryCode()
                employeeRecord.setSeniority(seniorityDate)
                employeeRecord.setMitdk()
                employeeRecord.setNemKonto(jobInfoRecord.custom_string25.text())

                //Placeholder fields
                employeeRecord.setRequestTaxCardType()
                employeeRecord.setRequestDate()
                employeeRecord.setInitials()

                recordsToMap.add(employeeRecord)

                //Employee record MLE32HRAN
                EmployeeMLE32HRAN employeeRecordMLE32HRAN = new EmployeeMLE32HRAN()

                employeeRecordMLE32HRAN.setCustomerId("894")
                employeeRecordMLE32HRAN.setEmployer("1")
                employeeRecordMLE32HRAN.setIdentification("MLE-32-HRAN")
                employeeRecordMLE32HRAN.setEmployee(employmentInfo.custom_string1.text())
                employeeRecordMLE32HRAN.setPersonIdExternal(personIdExternal)
                employeeRecordMLE32HRAN.setFromDate(startDate)
                employeeRecordMLE32HRAN.setTerminationCode() //empty for hires
                employeeRecordMLE32HRAN.setTerminationDate() //empty for hires
                employeeRecordMLE32HRAN.setPositionCategory(jobInfoRecord.custom_string26.text())

                def standardHours = jobInfoRecord.standard_hours.text() as BigDecimal
                def monthlyHours = (standardHours * 52) / 12
                def roundedMonthlyHours = monthlyHours.setScale(2, BigDecimal.ROUND_HALF_UP)
                employeeRecordMLE32HRAN.setStandardTimePerMonth(roundedMonthlyHours.toString())

                employeeRecordMLE32HRAN.setStandardTime("160,33")
                employeeRecordMLE32HRAN.setSalaryGroup(jobInfoRecord.custom_string27.text())
                employeeRecordMLE32HRAN.setCostCenter(jobInfoRecord.cost_center.text())

                //Placeholder fields
                employeeRecordMLE32HRAN.setCommencementDate()
                employeeRecordMLE32HRAN.setTitle()

                recordsToMapMLE32HRAN.add(employeeRecordMLE32HRAN)
            }
        }
        // do the mapping
        message.setBody("<records></records>")
        if (!recordsToMap.isEmpty() && !recordsToMapMLE32HRAN.isEmpty()) {
            StringWriter stringWriter = new StringWriter()
            MarkupBuilder markupBuilder = new MarkupBuilder(stringWriter)
            markupBuilder."records" {
                recordsToMap.each { Employee rec ->
                    markupBuilder."record"(person_id_external: rec.getPersonIdExternal()) {
                        CustomerId(rec.getCustomerId())
                        Employer(rec.getEmployer())
                        Identification(rec.getIdentification())
                        Employee(rec.getEmployee())
                        CPRNo(rec.getCprNo())
                        CVRNo(rec.getCvrNo())
                        LastName(rec.getLastName())
                        FirstName(rec.getFirstName())
                        Address1(rec.getAddress1())
                        Address2(rec.getAddress2())
                        PostalCode(rec.getPostalCode())
                        ForeignPostalDistrict(rec.getForeignPostalDistrict())
                        FirstTimeEmployment(rec.getFirstTimeEmployment())
                        Email(rec.getEmail())
                        CountryCode(rec.getCountryCode())
                        Seniority(rec.getSeniority())
                        RequestTaxCardType(rec.getRequestTaxCardType())
                        RequestDate(rec.getRequestDate())
                        Mitdk(rec.getMitdk())
                        Initials(rec.getInitials())
                        NemKonto(rec.getNemKonto())
                    }
                }
                recordsToMapMLE32HRAN.each { EmployeeMLE32HRAN rec ->
                    markupBuilder."record"(person_id_external: rec.getPersonIdExternal()) {
                        CustomerId(rec.getCustomerId())
                        Employer(rec.getEmployer())
                        Identification(rec.getIdentification())
                        Employee(rec.getEmployee())
                        FromDate(rec.getFromDate())
                        CommencementDate(rec.getCommencementDate())
                        TerminationCode(rec.getTerminationCode())
                        TerminationDate(rec.getTerminationDate())
                        PositionCategory(rec.getPositionCategory())
                        Title(rec.getTitle())
                        StandardTimePerMonth(rec.getStandardTimePerMonth())
                        StandardTime(rec.getStandardTime())
                        SalaryGroup(rec.getSalaryGroup())
                        CostCenter(rec.getCostCenter())
                    }
                }
            }
            message.setBody(stringWriter.toString())
        }
    }
    return message
}

static getRelevantNode(NodeList nodeList, Date today) {
    if(nodeList){
        Node relevantNode = nodeList.first()
        if(nodeList.size() > 1){
            nodeList.each{ Node node ->
                String startDate = node.start_date.text()
                Date start_date = Date.parse( "yyyy-MM-dd", startDate )
                String endDate = node.end_date.text() ? node.end_date.text() : "9999-12-31"
                Date end_date = Date.parse( "yyyy-MM-dd", endDate )

                String aktStartDate = relevantNode.start_date.text()
                Date akt_start_date = Date.parse( "yyyy-MM-dd", aktStartDate )
                String aktEndDate = relevantNode.end_date.text() ? relevantNode.end_date.text() : "9999-12-31"
                Date akt_end_date = Date.parse( "yyyy-MM-dd", aktEndDate )
                if(start_date < akt_start_date && end_date >= today){
                    relevantNode = node
                }

            }
            return relevantNode
        }
        else{
            return relevantNode
        }
    }
    else{
        return null
    }
}


static getRelevantJobInfoNodes(NodeList jobInfoNodes, List<String> fieldsToCheck, String suffixPrevious) {
    NodeList nodes = new NodeList()
    try {
        nodes = jobInfoNodes.findAll { Node jobInfoRec ->
            String action = jobInfoRec.action.text()
            String eventReason = jobInfoRec.event_reason.text()
            return action && eventReason && eventReason != "discard" &&     \
                        (
                    action == "INSERT" ||     \
                            (action == "CHANGE" && Utils.hasRelevantFieldChange(jobInfoRec, fieldsToCheck, suffixPrevious))
            )
        }
    } catch (Exception e) {
        //
    }
    return nodes
}
