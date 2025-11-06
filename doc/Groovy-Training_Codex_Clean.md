# Groovy Training for SAP CPI — Codex Edition

_Converted from PowerPoint and cleaned for use in ChatGPT Codex. Code blocks are fenced and syntax highlighted._


## Slide 1

**Groovy Training**
- Master Groovy scripting for SAP CPI integration development. From fundamentals to advanced techniques.

## Slide 2

**Variables in Groovy**
**Dynamic Typing**
- Use def for runtime type resolution. Flexible and reduces boilerplate in scripts.
**Static Typing**
- Declare with explicit types like int, String for better IDE support and type safety.

- String employeeName = "Alice Johnson"int employeeId = 1001double salary = 55000.0boolean isActive = truedef department = "Finance"def yearsOfService = 3
- Variables can be reassigned unless declared final. Naming follows Java standards and is case-sensitive.

## Slide 3

**Data Types**

**Primitives**
- int, double, boolean, char

**Objects**
- String, BigDecimal (default for floating point)

**Type Checking**
- Use .getClass().name to inspect variable types

**Coercion**
**Convert types using as or explicit casting**

## Slide 4

**Operators**

**Arithmetic**
- +, -, *, / for calculations

**Relational**
- >, ==, != for comparisons

**Logical**
- &&, ||, ! for boolean logic

**Assignment**
- +=, -=, *= for compound operations
- Groovy's operator overloading maps symbols to methods internally. == checks value equality, is checks object identity.

## Slide 5

**Operator Example**
**Salary Calculation**
- Combining arithmetic and logical operators for employee compensation logic.


```json
int base = 50000int bonus = 5000println "Total: ${base + bonus}"println "Net: ${base - 2000}"println "Monthly: ${base / 12}"boolean eligible = base > 45000 && bonus >= 5000println "Eligible? $eligible"
```


## Slide 6

**Conditionals**
- if/else
**Decision making based on boolean expressions**
- switch
- Supports ranges, collections, and types
**Truthy Values**
**Non-null and non-zero treated as true**
- Groovy's switch is more flexible than Java's, evaluating types and ranges for powerful conditional logic.

## Slide 7

**Conditional Example**


```json
int years = 6String performance = "Excellent"if (years >= 5 && performance == "Excellent") { println "Eligible for promotion"} else { println "Regular review"}int rating = 4switch (rating) { case 5: case 4: println "Great"; break case 3: println "Good"; break default: println "Review needed"}
```


## Slide 8

**Loops**
- 01

- for Loop
**Enhanced iteration over collections and ranges directly**
- 02

- while Loop
- Useful when exit condition isn't based on iteration
- 03

**Collection Methods**
- each, collect, find as functional alternatives
- 04

**Control Flow**
**Use break and continue for loop control**

## Slide 9

**Loop Example**
**For Loop**


```json
def names = ["Alice", "Bob", "Carol"]for (n in names) {    println "Name: $n"}
```

**While Loop**


```json
int i = 0while (i < names.size()) {    println "Indexed: ${names[i]}"    i++}
```

- Groovy introduces range syntax like 1..5 to simplify loop conditions and iteration.

## Slide 10

**Exception Handling**

- 1

```groovy
try
```

**Contains code that might throw exceptions**

- 2

```groovy
catch
```

**Handles specific exception types to avoid crashes**

- 3

```groovy
finally
```

- Always executes, often used for cleanup
- Groovy supports multi-catch and doesn't enforce checked exceptions like Java. Always use specific exceptions for better error handling.

## Slide 11

**Exception Example**


```json
try {    def res = 10 / 0} catch (ArithmeticException e) {    println "Cannot divide: ${e.message}"} finally {    println "Always runs"}
```


- Use specific exception types rather than catching Exception or Throwable unless necessary for robust error handling.

## Slide 12

**Methods**

**Encapsulation**
**Reusable logic blocks that improve code modularity**

**Default Parameters**
**Methods support default values and omit return keyword**

**Named Arguments**
**Support named arguments using maps for clarity**

**Overloading**
- Multiple methods with same name, different parameters

## Slide 13

**Method Example**
**Annual Bonus Calculation**
- Method with default parameter demonstrating Groovy's concise syntax.


```json
def annualBonus(double salary,  double percent = 10) { return (salary * percent / 100).toInteger()}println "Bonus: " + annualBonus(60000)println "Custom: " + annualBonus(60000, 15)
```


## Slide 14

**Closures**
- Anonymous code blocks that can be passed as arguments or assigned to variables. First-class citizens in Groovy.

## Slide 15

**Closure Features**

**Anonymous Functions**

```groovy
Declared with curly braces {}, can take parameters and return values
```


**Scope Access**
**Access and modify variables defined outside their scope**

**Collection Methods**
- Used with each, findAll, collect

**Implicit Parameters**
**Support it for single-argument use**

## Slide 16

**Closure Example**


```json
def greet = { name -> println "Hello, $name!" }greet("Alice")def nums = [10, 20, 30]def above15 = nums.findAll { it > 15 }println "Filtered: $above15"
```

- Closures enable functional programming patterns and improve readability in compact operations.

## Slide 17

**Lists**

**Ordered Collections**
- Implemented as ArrayList, hold any type, dynamically resizable

**Construction**
- Use square brackets [] for concise list creation

**Manipulation**
- Use <<, add, remove for element operations

**Utility Methods**
- each, find, collect, sort for processing

## Slide 18

**List Example**


```json
def emps = ["John", "Jane"]emps << "Jim"emps.remove("Jane")emps.each { println "Emp: $it" }
```

**Key Operations**
- Add elements with <<
**Remove by value**
**Iterate with each**
**Access by index**

## Slide 19

**Maps**
**Key-Value Pairs**
- Unordered collections using [:] syntax
**Access Methods**
**Dot or bracket notation for value retrieval**
**Manipulation**
- find, collectEntries, groupBy methods
**Flexible Structure**
- Support nesting, iteration, dynamic properties

## Slide 20

**Map Example**


```json
def emp = [id: 101, name: "Alice", dept: "HR"]println emp.nameemp.each { k, v -> println "$k -> $v" }
```

- Maps are ideal for JSON-like structures and play a vital role in message context handling in integration scenarios.

## Slide 21

**Ranges**

- 1
**Inclusive**
- Use .. for inclusive ranges like 1..5

- 2
**Exclusive**
- Use ..< for exclusive upper bound

- 3
**Types**
- Works with numbers, characters, dates

- 4
**Operations**
- Iterate, check membership, slice, reverse

## Slide 22

**Range Example**
**Numeric Range**


```json
def r = 1..5r.each { println "Year: $it" }
```

**Character Range**


```json
def letters = 'A'..'D'println "Letters: $letters"
```

- Ranges provide clean syntax in for-loops and switch statements, improving readability for consecutive values.

## Slide 23

**Classes and Objects**

**Class Definition**
**Blueprints containing fields and methods**

**Auto-Generation**
- Getters, setters, constructors, toString auto-created

**Object Creation**

```groovy
Use new keyword or dynamic expansion
```


**Encapsulation**
- Properties public by default, accessible via dot notation

## Slide 24

**Class Example**


```json
class Employee { String name double salary void raise(double pct) {  salary += salary * pct / 100  }}def e = new Employee(name: "Alice", salary: 50000)e.raise(10)println "${e.name}'s new salary: ${e.salary}"
```


## Slide 25

**Inheritance**

**Superclass**
**Base class with common functionality**

**Subclass**

```groovy
Extends parent using extends keyword
```


**Override**
**Customize behavior with method overriding**

**Super Reference**
**Access parent class with super keyword**

## Slide 26

**Inheritance Example**


```groovy
class Person {    String name    void intro() {         println "I am $name"     }}class Manager extends Person {    String dept    void manage() {         println "Managing $dept"     }}
```



```json
def m = new Manager(    name: "Alice",     dept: "IT")m.intro()m.manage()
```

- Subclass inherits all public and protected members from superclass.

## Slide 27

**Abstract Classes**

- 1
**Template Definition**
- Base templates with partial implementation and required methods

- 2
**Abstract Methods**
**Subclasses must implement all abstract methods**

- 3
**No Instantiation**
- Cannot create instances directly, only through subclasses

- 4
**Structure Enforcement**
**Useful for enforcing structure in large applications**

## Slide 28

**Abstract Class Example**


```json
abstract class Staff {    String name    abstract String role()}class Intern extends Staff {    String role() { "Internship" }}def i = new Intern(name: "Jake")println "${i.name} - ${i.role()}"
```

- Abstract classes promote DRY principles and separation of concerns in object-oriented design.

## Slide 29

**XML Processing**
- Groovy provides powerful tools for parsing, transforming, and generating XML documents.

## Slide 30

**Parsing XML**

**XmlSlurper**
- Event-based, lazy reading, uses less memory. Preferred for large documents.

**XmlParser**
- Builds full DOM tree in memory, enables random access.

**GPath Navigation**
- Intuitive XPath-like navigation for accessing elements and attributes.

**Object-like Access**
- Parsed XML behaves like Groovy objects with dot and bracket notation.

## Slide 31

**XML Parsing Example**


```json
import groovy.util.XmlSlurperimport groovy.util.XmlParserdef xml = "Alice"def slurper = new XmlSlurper().parseText(xml)println "Slurper name: ${slurper.emp.name}"def parser = new XmlParser().parseText(xml)println "Parser name: ${parser.emp[0].name.text()}"
```


## Slide 32

**Transforming XML**
**Parse**
**Load XML using XmlParser**
**Modify**
- Change node values, add/delete elements
**Serialize**
- Use XmlUtil.serialize() to generate output
- Transformations are common in SAP CPI when changing third-party payloads. Groovy's features reduce boilerplate code.

## Slide 33

**Transforming XML**
**Parse**
**Load XML using XmlParser**
**Modify**
- Change node values, add/delete elements
**Serialize**
- Use XmlUtil.serialize() to generate output
- Transformations are common in SAP CPI when changing third-party payloads. Groovy's features reduce boilerplate code.

## Slide 34

- 01

- r
**Captures output as string for further processing**
- 02

**Method Names**
**Use method and property names to define XML structure**
- 03

**Output**
- Readable, properly escaped, and compact XML

## Slide 35

**XML Generation Example**


```json
import groovy.xml.MarkupBuilderdef xml = "Alice"def parsed = new XmlSlurper().parseText(xml)def w = new StringWriter()def builder = new MarkupBuilder(w)builder.summary { parsed.emp.each { employee(id: it.@id, it.name.text()) }}println w.toString()
```


## Slide 36

**JSON Processing**
- Groovy's JsonSlurper makes parsing and transforming JSON simple and intuitive.

## Slide 37

**Parsing JSON**

- 1
**JsonSlurper**
**Parses JSON strings into Groovy Maps and Lists**

- 2
**Access Methods**
**Use dot or bracket notation for property access**

- 3
**Deep Nesting**
**Supports complex nested structures efficiently**

- 4
**Collections**
- JSON arrays become Lists, objects become Maps

## Slide 38

**JSON Parsing Example**


```json
import groovy.json.JsonSlurperdef json = '{"id":"E1","name":"Bob","skills":["Java","Groovy"]}'def data = new JsonSlurper().parseText(json)println "Name: ${data.name}, Skill 1: ${data.skills[0]}"
```

- JSON is often used in APIs and is a key format in SAP CPI integrations for modern web services.

## Slide 39

**Transforming JSON**

- 1
**Parse**
**Use JsonSlurper to load JSON**

- 2
**Modify**
**Change values like normal Groovy objects**

- 3
**Serialize**
- Use JsonOutput.toJson() to convert back

- 4
**Format**
- JsonOutput.prettyPrint() for readability

## Slide 40

**JSON Transform Example**


```json
import groovy.json.JsonSlurperimport groovy.json.JsonOutputdef json = '{"emps":[{"name":"Alice","salary":50000}]}'def data = new JsonSlurper().parseText(json)
```



```groovy
data.emps.each { it.salary += 5000 }println JsonOutput.prettyPrint( JsonOutput.toJson(data))
```


## Slide 41

**Combining XML Documents**
- Merge multiple XML documents by parsing and appending nodes. Useful for combining data from multiple sources.


```groovy
import groovy.util.XmlParserimport groovy.xml.XmlUtildef xml1 = "1"def xml2 = "2"def parser = new XmlParser()def root1 = parser.parseText(xml1)def root2 = parser.parseText(xml2)root2.employee.each { root1.append(it) }println XmlUtil.serialize(root1)
```


## Slide 42

**Sorting Collections**

**List Sorting**
- Use sort() or custom comparators for ordering

**Map Sorting**

```groovy
Sort by key or value using sort { it.key }
```


**Use Cases**
- Reporting, displaying data, normalization

## Slide 43

**Sorting Example**
**List**


```json
def numbers = [5, 1, 3, 2, 4]println "Sorted: " + numbers.sort()
```

**Map by Value**


```xml
def employees = [ Anna: 5000,  Ben: 4500,  Carl: 5500]def sorted = employees.sort {  a, b -> a.value <=> b.value }println "Sorted: $sorted"
```


## Slide 44

**Regular Expressions**

**Match Operators**
- ==~ for entire string, =~ for partial match

**Validation**
- Check email, phone, and other format patterns

**Search**
**Find patterns within strings for data extraction**

**Data Cleaning**
**Remove or replace unwanted characters**

## Slide 45

**Regex Example**


```groovy
def name = "GroovyScript"println name ==~ /Groovy.*/ // starts withprintln name ==~ /.*Script/ // ends withprintln name =~ /.*oo.*/ // contains 'oo'// Email validationdef email = "user@example.com"println email ==~ /^[\w.%+-]+@[\w.-]+\.[A-Za-z]{2,}$/// Phone validationdef phone = "+1-202-555-0173"println phone ==~ /^\+?\d{1,3}[- ]?\(?\d{2,4}\)?[- ]?\d{3}[- ]?\d{4}$/
```


## Slide 46

**SAP CPI Integration**
- Groovy scripting in SAP Cloud Platform Integration for message processing and transformation.

## Slide 47

**Message Object**
**Body**
- Payload retrieved with getBody()
**Headers**
**HTTP metadata and Camel routing info**
**Properties**
**Runtime values for parameterization**
**Setters**
- Use setHeader() and setProperty()

## Slide 48

**Message Access Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messagedef Message processData(Message message) {    def body = message.getBody(java.io.Reader)    def myProp = message.getProperties().get("property_name")    def myHeader = message.getHeaders().get("header_name")        return message}
```

- Always use null checks when accessing optional headers or properties to prevent runtime errors.

## Slide 49

**MPL Logging**
**MessageLogFactory**
**Interface to append content to MPL**
**Attachments**
**Add strings to trace-level logs**
**Custom Headers**
**Surface key data in trace summaries**
**Visibility**
**Improve debugging and supportability**

## Slide 50

**MPL Logging Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messageimport com.sap.it.api.logging.MessageLogdef Message processData(Message message) { def messageLog = messageLogFactory.getMessageLog(message) def body = message.getBody(String)  if (messageLog != null) { messageLog.addAttachmentAsString("Payload", body, "text/plain") messageLog.addCustomHeaderProperty("MyKey", "MyValue") }  return message}
```


## Slide 51

**Throwing Errors**

**Interrupt Processing**
**Explicitly throw errors to stop flow based on custom logic**

**Validation**
**Enforce conditions that must be met before proceeding**

**Error Messages**
**Visible in CPI monitor and trace for debugging**

**Fallback Logic**
**Trigger error subprocesses or alternative paths**

## Slide 52

**Error Throwing Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messagedef Message processData(Message message) {    throw new Exception("Validation failed for input payload")}
```


- Keep exception messages readable and concise. Enrich with context to ease debugging in production environments.

## Slide 53

**Secure Parameters**

- 1
**SecureStoreService**
**Access credentials via ITApiFactory**

- 2
**Alias Reference**
**Reference by alias configured in Security Materials**

- 3
**Credential Types**
- getUserCredential() for user/password pairs

- 4
**Security**
**Never log or print secure parameters**

## Slide 54

**Secure Parameter Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messageimport com.sap.it.api.ITApiFactoryimport com.sap.it.api.securestore.SecureStoreServicedef Message processData(Message message) { def alias = message.getProperty("CredentialAlias") def secureStore = ITApiFactory.getService(SecureStoreService.class, null) def creds = secureStore.getUserCredential(alias)  message.setProperty("username", creds.getUsername()) message.setProperty("password", creds.getPassword().toString())  return message}
```


## Slide 55

**Value Mapping**
**Central Definition**
**Map external values to internal equivalents**
**ValueMappingApi**
**Access deployed mappings via API**
**Reusable Logic**
**Reduce hardcoding of conditionals**
**Adaptability**
**Make integrations flexible to external changes**

## Slide 56

**Value Mapping Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messageimport com.sap.it.api.ITApiFactoryimport com.sap.it.api.mapping.ValueMappingApidef Message processData(Message message) { def vmApi = ITApiFactory.getApi(ValueMappingApi.class, null) def value = vmApi.getMappedValue( "source-agency",  "source-id",  "source-value",  "target-agency",  "target-id" ) message.setProperty("mappedValue", value) return message}
```


## Slide 57

**URL GET Parameters**
- 01

**CamelHttpQuery**
**HTTP GET parameters captured in this header**
- 02

**Parse Query String**
- Split on & and = to extract parameters
- 03

**Store as Properties**
**Make parameters available to subsequent steps**
- 04

**Dynamic Control**
- Support pagination, filters, input-driven logic

## Slide 58

**GET Parameters Example**


```groovy
import com.sap.gateway.ip.core.customdev.util.Messagedef Message processData(Message message) {    def queryString = message.getHeaders().get("CamelHttpQuery")        if (queryString) {        queryString.split("&").each { pair ->            def (k, v) = pair.split("=")            message.setProperty(k, v)        }    }        return message}
```

- Use URL decoding if parameter values include encoded characters. Handle null cases gracefully.

## Slide 59

**Best Practices**
**Null Safety**
- Always check for null values when accessing headers, properties, or optional data.
**Security**
- Never log sensitive data. Use secure parameters for credentials and tokens.
**Performance**
- Use XmlSlurper for large documents. Avoid unnecessary loops and transformations.
**Testing**
- Test transformations thoroughly for structural and encoding correctness.
**Documentation**
- Comment complex logic. Use descriptive variable and method names.
**Error Handling**
- Use specific exceptions. Provide clear error messages with context.