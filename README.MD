# Anotação Top

### Lista de Anotacoes
- Spring Web
- Spring Boot Dev Tools
- Spring Data JPA
- MySQL Driver

___

## application.properties
```properties
spring.application.name=foodta

# Creates the database if there isn't one, and sets the timezone to UTC
spring.datasource.url=jdbc:mysql://localhost:3306/foodta?createDatabaseIfNotExist=true&serverTimezone=UTC
# "Define" the user
spring.datasource.username=root
# Leave empty so there is no password
spring.datasource.passowrd=

# Logs creation of the SQL code
spring.jpa.generate-ddl=true
# re-creates the database everytime the code is run.
spring.jpa.hibernate.ddl-auto=create
```

___

## Lombok

- @Data
  - @Getter
  - @Setter
  - @EqualsAndHashCode
    - [ (onlyExplicitlyIncluded = true|false) ]
    - [ .Include ]

___

## pom.xml
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>

```