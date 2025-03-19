package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Cozinha;
import com.enzo.foodta.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
  @Autowired
  private CozinhaRepository cozinhaRepository;
  @GetMapping
  public List<Cozinha> listar() {
    return cozinhaRepository.listar();
  }
}
