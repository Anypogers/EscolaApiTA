package com.enzo.foodta.infrastructure.repository;

import com.enzo.foodta.domain.model.Cidade;
import com.enzo.foodta.domain.repository.CidadeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
  @PersistenceContext
  private EntityManager manager;
  @Override
  public List<Cidade> listar() {
    return manager.createQuery("from Cidade", Cidade.class).getResultList();
  }

  @Override
  public Cidade buscar(Long id) {
    return manager.find(Cidade.class, id);
  }

  @Transactional
  @Override
  public Cidade salvar(Cidade cidade) {
    return manager.merge(cidade);
  }

  @Transactional
  @Override
  public void remover(Long id) {
    Cidade cidade = buscar(id);
    manager.remove(cidade);
  }
}
