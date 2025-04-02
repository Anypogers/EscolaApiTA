package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Cidade;
import com.enzo.foodta.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
  @Autowired
  private CidadeRepository cidadeRepository;
  @GetMapping
  public List<Cidade> listar() {
    return cidadeRepository.listar();
  }

  @GetMapping("/{cidadeId}")
  public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
    Cidade cidade = cidadeRepository.buscar(cidadeId);
    if (cidade != null) {
      return ResponseEntity.ok(cidade);
    }
    return ResponseEntity.notFound().build();
  }
}
