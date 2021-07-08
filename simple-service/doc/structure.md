# Project Structure

```
.
|-- application
|   |-- conf/{env}/resources
|   |   |-- application-env.yml ==> Declare all data source connection attributes
|   |-- src
|       |-- main
|       |   |-- java
|       |   |   |-- lab/neobradley/simple
|       |   |       |-- application
|       |   |       |   |-- config          ==> Swagger and MyBatis configuration
|       |   |       |   |   |-- ds          ==> Detail config for switch ds
|       |   |       |   |-- controller      ==> API for external access
|       |   |       |   |   |-- v1          ==> RESTful APIs for V1, add V{x} if necessary
|       |   |       |   |   |   |-- request ==> Request beans
|       |   |       |   |-- dto             ==> Beans for response of API
|       |   |       |   |   |-- mapper      ==> Translator of dao to dto
|       |   |       |   |   |-- model       ==> Business response objects
|       |   |       |   |   |-- response    ==> Generic response objects
|       |   |       |   |-- exception       ==> Exception classes and handling
|       |   |       |-- constant            ==> App contants and enums 
|       |   |       |-- domain/{domains}    ==> Business domain 
|       |   |       |   |-- aggregate       ==> Aggregates for cross-service logic
|       |   |       |   |-- bo              ==> Business object, for special business requirement
|       |   |       |   |-- model           ==> Database ORM objects
|       |   |       |   |-- repository      ==> Database CRUD layer, each repository package relate to each model
|       |   |       |   |-- service         ==> Service layer, each service package relate to each repository except for aggregate
|       |   |       |-- util                ==> Generic utilities
|       |   |-- resources
|       |   |   |-- application.yml         ==> app core configuration
|       |   |   |-- application-common.yml  ==> app actuator and other common configuration
|       |   |   |-- logback-boot.xml        ==> app log format configuration
|       |-- test
|           |-- java
|           |   |-- lab/neobradley/simple
|           |-- resources
|
|-- doc             ==> Documentations for project details
|
|-- gradle          ==> Specific purpose gradle files
|   |-- check       ==> Declaration of checks
|   |-- wrapper     ==> Gradle default files
|   app.gradle      ==> Declaration of application build/run
|   check.gradle    ==> Declaration of checking build/run (CheckStyle/PMD/Spotbugs)
|   conf.gradle     ==> Declaration of different environment properties replacement build/run
|   docs.gradle     ==> Declaration of swagger doc tool build/run
|   project.gradle  ==> Declaration of project build/run
|
|-- lib             ==> Specific lib not found in maven central
|
build.gradle        ==> Declaration of version, module, and dependencies build/run
gradle.properties   ==> Gradle properties
README.md           ==> Documentation for introduction
settings.gradle     ==> Gradle project settings
```

---
References

- [Spring Boot Starterkit](https://github.com/khandelwal-arpit/springboot-starterkit)
