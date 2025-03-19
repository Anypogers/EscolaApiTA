package com.enzo.foodta.domain.repository;

import com.enzo.foodta.domain.model.Restaurante;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestauranteRepository {
  List<Restaurante> listar();

  Restaurante buscar(Long id);

  Restaurante salvar(Restaurante restaurante);

  void remover(Long id);
}
