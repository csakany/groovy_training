package src.main.resources.script.CA

class EmployeeMLE32HRAN {

    private String personIdExternal

    private String customerId
    private String employer
    private String identification
    private String employee
    private String fromDate
    private String commencementDate
    private String terminationCode
    private String terminationDate
    private String positionCategory
    private String title
    private String standardTimePerMonth
    private String standardTime
    private String salaryGroup
    private String costCenter

    // Constructor
    EmployeeMLE32HRAN() {}


    EmployeeMLE32HRAN(String customerId, String employer, String identification, String employee,
                      String fromDate, String commencementDate, String terminationCode, String terminationDate,
                      String positionCategory, String title, String standardTimePerMonth, String standardTime,
                      String salaryGroup, String costCenter) {
        this.customerId = customerId
        this.employer = employer
        this.identification = identification
        this.employee = employee
        this.fromDate = fromDate
        this.commencementDate = commencementDate
        this.terminationCode = terminationCode
        this.terminationDate = terminationDate
        this.positionCategory = positionCategory
        this.title = title
        this.standardTimePerMonth = standardTimePerMonth
        this.standardTime = standardTime
        this.salaryGroup = salaryGroup
        this.costCenter = costCenter
    }

    // Getters and Setters
    String getCustomerId() { return customerId }
    void setCustomerId(String customerId) { this.customerId = customerId }

    String getEmployer() { return employer }
    void setEmployer(String employer) { this.employer = employer }

    String getIdentification() { return identification }
    void setIdentification(String identification) { this.identification = identification }

    String getEmployee() { return employee }
    void setEmployee(String employee) { this.employee = employee }

    String getFromDate() { return fromDate }
    void setFromDate(String fromDate) { this.fromDate = fromDate }

    String getCommencementDate() { return commencementDate }
    void setCommencementDate(String commencementDate) { this.commencementDate = commencementDate }

    String getTerminationCode() { return terminationCode }
    void setTerminationCode(String terminationCode) { this.terminationCode = terminationCode }

    String getTerminationDate() { return terminationDate }
    void setTerminationDate(String terminationDate) { this.terminationDate = terminationDate }

    String getPositionCategory() { return positionCategory }
    void setPositionCategory(String positionCategory) { this.positionCategory = positionCategory }

    String getTitle() { return title }
    void setTitle(String title) { this.title = title }

    String getStandardTimePerMonth() { return standardTimePerMonth }
    void setStandardTimePerMonth(String standardTimePerMonth) { this.standardTimePerMonth = standardTimePerMonth }

    String getStandardTime() { return standardTime }
    void setStandardTime(String standardTime) { this.standardTime = standardTime }

    String getSalaryGroup() { return salaryGroup }
    void setSalaryGroup(String salaryGroup) { this.salaryGroup = salaryGroup }

    String getCostCenter() { return costCenter }
    void setCostCenter(String costCenter) { this.costCenter = costCenter }

    String getPersonIdExternal() { return personIdExternal }
    void setPersonIdExternal(String personIdExternal) { this.personIdExternal = personIdExternal }
}