package src.main.resources.script

class Employee {
    private String personIdExternal
    private String customerId
    private String employer
    private String identification
    private String employee
    private String cprNo
    private String cvrNo
    private String lastName
    private String firstName
    private String address1
    private String address2
    private String postalCode
    private String foreignPostalDistrict
    private String firstTimeEmployment
    private String email
    private String countryCode
    private String seniority
    private String requestTaxCardType
    private String requestDate
    private String mitdk
    private String initials
    private String nemKonto

    // Constructor
    Employee() {}

    // Constructor with all fields
    Employee(String customerId, String employer, String identification, String employee,
                    String cprNo, String cvrNo, String lastName, String firstName,
                    String address1, String address2, String postalCode, String foreignPostalDistrict,
                    String firstTimeEmployment, String email, String countryCode, String seniority,
                    String requestTaxCardType, String requestDate, String mitdk,
                    String initials, String nemKonto) {
        this.customerId = customerId
        this.employer = employer
        this.identification = identification
        this.employee = employee
        this.cprNo = cprNo
        this.cvrNo = cvrNo
        this.lastName = lastName
        this.firstName = firstName
        this.address1 = address1
        this.address2 = address2
        this.postalCode = postalCode
        this.foreignPostalDistrict = foreignPostalDistrict
        this.firstTimeEmployment = firstTimeEmployment
        this.email = email
        this.countryCode = countryCode
        this.seniority = seniority
        this.requestTaxCardType = requestTaxCardType
        this.requestDate = requestDate
        this.mitdk = mitdk
        this.initials = initials
        this.nemKonto = nemKonto
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

    String getCprNo() { return cprNo }
    void setCprNo(String cprNo) { this.cprNo = cprNo }

    String getCvrNo() { return cvrNo }
    void setCvrNo(String cvrNo) { this.cvrNo = cvrNo }

    String getLastName() { return lastName }
    void setLastName(String lastName) { this.lastName = lastName }

    String getFirstName() { return firstName }
    void setFirstName(String firstName) { this.firstName = firstName }

    String getAddress1() { return address1 }
    void setAddress1(String address1) { this.address1 = address1 }

    String getAddress2() { return address2 }
    void setAddress2(String address2) { this.address2 = address2 }

    String getPostalCode() { return postalCode }
    void setPostalCode(String postalCode) { this.postalCode = postalCode }

    String getForeignPostalDistrict() { return foreignPostalDistrict }
    void setForeignPostalDistrict(String foreignPostalDistrict) { this.foreignPostalDistrict = foreignPostalDistrict }

    String getFirstTimeEmployment() { return firstTimeEmployment }
    void setFirstTimeEmployment(String firstTimeEmployment) { this.firstTimeEmployment = firstTimeEmployment }

    String getEmail() { return email }
    void setEmail(String email) { this.email = email }

    String getCountryCode() { return countryCode }
    void setCountryCode(String countryCode) { this.countryCode = countryCode }

    String getSeniority() { return seniority }
    void setSeniority(String seniority) { this.seniority = seniority }

    String getRequestTaxCardType() { return requestTaxCardType }
    void setRequestTaxCardType(String requestTaxCardType) { this.requestTaxCardType = requestTaxCardType }

    String getRequestDate() { return requestDate }
    void setRequestDate(String requestDate) { this.requestDate = requestDate }

    String getMitdk() { return mitdk }
    void setMitdk(String mitdk) { this.mitdk = mitdk }

    String getInitials() { return initials }
    void setInitials(String initials) { this.initials = initials }

    String getNemKonto() { return nemKonto }
    void setNemKonto(String nemKonto) { this.nemKonto = nemKonto }

    String getPersonIdExternal() { return personIdExternal }
    void setPersonIdExternal(String personIdExternal) { this.personIdExternal = personIdExternal }
}