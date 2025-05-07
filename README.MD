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

## api.controller

```java
package com.joe.codebase.api.controller;

import com.joe.codebse.domain.exception.EntidadeEmUsoException;
import com.joe.codebse.domain.exception.EntidadeNaoEncontradaException;
import com.joe.codebse.domain.model.Food;
import com.joe.codebse.domain.repository.FooRepository;
import com.joe.codebse.domain.service.FooService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Foos")
public class FooController {
  
  @Autowired
  private FooRepository fooRepository;
  
  @GetMapping
  public List<Foo> listar() {
    return fooRepository.findAll();
  }

  @GetMapping("/{fooId}")
  public ResponseEntity<Foo> buscar(@PathVariable Long fooId) {
    Optional<Foo> foo = fooRepository.findById(fooId);
    if (foo.isPresent()) {
      return ResponseEntity.ok(foo.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  public ResponseEntity<Foo> adicionar(@RequestBody Foo foo) {
    foo = fooService.salvar(foo);
    return ResponseEntity.status(HttpStatus.CREATED).body(foo);
  }

  @PutMapping("/{fooId}")
  public ResponseEntity<Foo> atualizar(@PathVariable Long fooId, @RequestBody Foo foo) {
    Optional<Foo> fooAtual = fooRepository.findById(fooId);
    if (fooAtual.isPresent()) {
      BeanUtils.copyProperties(foo, fooAtual, "id");
      Foo fooSalva = fooService.salvar(fooAtual.get());
      return ResponseEntity.ok(fooSalva);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{fooId}")
  public ResponseEntity<Foo> remover(@PathVariable Long fooId) {
    try {
      fooService.excluir(fooId);
      return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
```
___

## domain.model
```java
package com.joe.codebase.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_foo")
public class Foo {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  @ManyToOne
  @JoinColumn(name = "bar_id")
  private Bar bar;
}
```

___

## domain.repository
```java
package com.joe.codebase.domain.repository;

import com.joe.codebase.domain.model.Foo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FooRepository extends JpaRepository<Foo, Long>{
  
}
```

___

## domain.service
```java
package com.joe.foodta.domain.service;

import com.joe.codebase.domain.exception.EntidadeEmUsoException;
import com.joe.codebase.domain.exception.EntidadeNaoEncontradaException;
import com.joe.codebase.domain.model.Foo;
import com.joe.codebase.domain.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FooService {
  @Autowired
  private FooRepository fooRepository;

  public Foo salvar(Foo foo) {
    return fooRepository.save(foo);
  }

  public void excluir(Long id) {
    try {
      fooRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format(
              "Foo ou código %d não pode ser removida, pois está em uso.", id
      ));
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(
              "Não existe cadastro de foo com código %d", id
      ));
    }
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