package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Estado;
import com.enzo.foodta.domain.repository.EstadoRepository;
import com.enzo.foodta.domain.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private EstadoService estadoService;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar (@RequestBody Estado estado) {
    return estadoService.salvar(estado);
  }
}
