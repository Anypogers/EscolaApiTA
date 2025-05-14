package com.enzo.foodta.domain.repository;

import com.enzo.foodta.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
  @Query("from Cidade c join fetch c.estado")
  List<Cidade> findAll();
}