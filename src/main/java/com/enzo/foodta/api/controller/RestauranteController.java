package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.Restaurante;
import com.enzo.foodta.domain.repository.RestauranteRepository;
import com.enzo.foodta.domain.service.RestauranteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private RestauranteService restauranteService;

  @GetMapping
  public List<Restaurante> listar(){
    return restauranteRepository.findAll();
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
    Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
    if (restaurante.isPresent()) {
      return ResponseEntity.ok(restaurante.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurante adicionar(@RequestBody Restaurante restaurante) {
    return restauranteService.salvar(restaurante);
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
    Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);
    if (restauranteAtual.isPresent()) {
      BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
      Restaurante restauranteSalva = restauranteService.salvar(restauranteAtual.get());
      return ResponseEntity.ok(restauranteSalva);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> remover(@PathVariable Long restauranteId) {
    try {
      restauranteService.excluir(restauranteId);
      return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
