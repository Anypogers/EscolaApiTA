package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Restaurante;
import com.enzo.foodta.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
  @Autowired
  private RestauranteRepository restauranteRepository;

  @GetMapping
  private List<Restaurante> listar(){
    return restauranteRepository.listar();
  }
}
