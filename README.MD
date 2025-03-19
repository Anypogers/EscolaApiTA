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

## Repository
```java
package com.joe.codebase.domain.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository {
  
}
```

## Repository Impl
```java
package com.joe.codebase.infrastructure.repository;

import com.joe.codebase.domain.repository.CozinhaRepository;

public class FooRepositoryImpl implements FooRepository {
}
```

___

## Controller

```java
package com.enzo.foodta.api.controller;

import com.joe.codebase.domain.model.Foo;
import com.enzo.foodta.domain.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Foos")
public class FooController {
  @Autowired
  private FooRepository fooRepository;
  @GetMapping
  public List<Cozinha> listar() {
    return fooRepository.listar();
  }
}
```

___

## pom.xml
```xml
<dependency>
  <groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
  <optional>true</optional>
</dependency>

```