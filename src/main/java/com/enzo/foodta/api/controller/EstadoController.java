package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Estado;
import com.enzo.foodta.domain.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
  @Autowired
  private EstadoRepository estadoRepository;

  @GetMapping
  private List<Estado> listar(){
    return estadoRepository.listar();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
    Estado estado = estadoRepository.buscar(estadoId);
    if (estado != null) {
      return ResponseEntity.ok(estado);
    }
    return ResponseEntity.notFound().build();
  }
}
