package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.Estado;
import com.enzo.foodta.domain.repository.EstadoRepository;
import com.enzo.foodta.domain.service.EstadoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {
  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private EstadoService estadoService;

  @GetMapping
  private List<Estado> listar(){
    return estadoRepository.findAll();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> buscar(@PathVariable Long estadoId){
    Optional<Estado> estado = estadoRepository.findById(estadoId);
    if (estado.isPresent()) {
      return ResponseEntity.ok(estado.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Estado adicionar (@RequestBody Estado estado) {
    return estadoService.salvar(estado);
  }

  @PutMapping("/{estadoId}")
  public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
    Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
    if (estadoAtual.isPresent()) {
      BeanUtils.copyProperties(estado, estadoAtual, "id");
      Estado estadoSalva = estadoService.salvar(estadoAtual.get());
      return ResponseEntity.ok(estadoSalva);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{estadoId}")
  public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
    try {
      estadoService.excluir(estadoId);
      return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
