package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Restaurante;
import com.enzo.foodta.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
  @Autowired
  private RestauranteRepository restauranteRepository;

  @GetMapping
  public List<Restaurante> listar(){
    return restauranteRepository.listar();
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteRepository.buscar(restauranteId);
    if (restaurante != null) {
      return ResponseEntity.ok(restaurante);
    }
    return ResponseEntity.notFound().build();
  }
}
