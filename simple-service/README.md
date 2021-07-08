# application

### Setup (MUST REVIEW)

- Java 1.8
- Gradle 6.3
- IntelliJ
    - Install Lombok plugin then restart
    - Build, Execution, Deployment -> Compiler -> Annotation Processor
        - Enable annotation processing (Check)
    - Editor -> Code Style -> Java
        - Imports
            - Class count to use import with * (999)
            - Name count to use static import with * (999)

### Run

- Init Gradle
    - Gradle panel -> refresh icon (Double click)
- Build
    - Gradle panel -> :bot-service -> Tasks -> build -> build (Double click)
- Run
    - Gradle panel -> :bot-service -> Tasks -> application -> bootRun (Double Click)
- Check
    - [Actuator](http://localhost:8080/actuator)
    - [Swagger UI](http://localhost:8080/doc.html)
    - [Swagger Api-Doc](http://localhost:8080/v2/api-docs)

### Domain

- TBD

### Test Strategy

- TBD

### Tech Architecture

- Check doc/structure.md for project structure
- TBD

### Deploy Architecture

- TBD

### External Dependency

- E03, E04, DC, RPT data sources

### Environment Info

- TBD

### Coding Practices

- Lombok
    - Use @Getter, @Setter, and necessary annotations only to avoid over generation of code
- MyBatisPlus
    - Use Wrappers.lambdaQuery() to avoid refactor issue
- Log
    - Use @Slf4j annotation only to inject logger, check level of log to avoid sudden increase of log size
- Swagger
    - Add description and sample as much as possible
- Validator
    - Use relevant validator to avoid redundant rule checking, if request object, must add @Valid annotation to be
      effective
    - Sample annotations
        - @Null 被注释的元素必须为 null
        - @NotNull 被注释的元素必须不为 null
        - @AssertTrue 被注释的元素必须为 true
        - @AssertFalse 被注释的元素必须为 false
        - @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
        - @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
        - @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
        - @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
        - @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
        - @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
        - @Past 被注释的元素必须是一个过去的日期
        - @Future 被注释的元素必须是一个将来的日期
        - @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
        - @NotBlank(message =)   验证字符串非null，且trim后长度必须大于0 不可用在数字类型
        - @Email 被注释的元素必须是电子邮箱地址
        - @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
        - @NotEmpty 被注释的字符串的必须非空
        - @Range(min=,max=,message=)  被注释的元素必须在合适的范围内
- Money
    - Use BigDecimal
- Date
    - Use LocalDateTime and LocalDate, convert to ZoneDateTime and ZoneDate if necessary, do not use Date
- Response
    - Use JSON format only

### FAQ

- DRY
- Explicit over implicit
