package com.enzo.foodta.infrastructure.repository;

import com.enzo.foodta.domain.model.Estado;
import com.enzo.foodta.domain.repository.EstadoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
  @PersistenceContext
  private EntityManager manager;
  @Override
  public List<Estado> listar() {
    return manager.createQuery("from Estado", Estado.class).getResultList();
  }

  @Override
  public Estado buscar(Long id) {
    return manager.find(Estado.class, id);
  }

  @Transactional
  @Override
  public Estado salvar(Estado estado) {
    return manager.merge(estado);
  }

  @Transactional
  @Override
  public void remover(Long id) {
    Estado estado = buscar(id);
    manager.remove(estado);
  }
}
