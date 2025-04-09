package com.enzo.foodta.domain.service;

import com.enzo.foodta.domain.model.Restaurante;
import com.enzo.foodta.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestauranteService {
  @Autowired
  private RestauranteRepository restauranteRepository;

  public Restaurante salvar(Restaurante restaurante) {
    return restauranteRepository.salvar(restaurante);
  }
}
