package com.enzo.foodta.infrastructure.repository;

import com.enzo.foodta.domain.model.Restaurante;
import com.enzo.foodta.domain.repository.RestauranteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
  @PersistenceContext
  private EntityManager manager;
  @Override
  public List<Restaurante> listar() {
    return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
  }

  @Override
  public Restaurante buscar(Long id) {
    return null;
  }

  @Override
  public Restaurante salvar(Restaurante restaurante) {
    return null;
  }

  @Override
  public void remover(Long id) {

  }
}
