package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.Cidade;
import com.enzo.foodta.domain.repository.CidadeRepository;
import com.enzo.foodta.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private CidadeService cidadeService;

  @GetMapping
  public List<Cidade> listar() {
    return cidadeRepository.findAll();
  }

  @GetMapping("/{cidadeId}")
  public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {
    Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
    if (cidade.isPresent()) {
      return ResponseEntity.ok(cidade.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cidade adicionar(@RequestBody Cidade cidade) {
    return cidadeService.salvar(cidade);
  }

  @PutMapping("/{cidadeId}")
  public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
    Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
    if (cidadeAtual.isPresent()) {
      BeanUtils.copyProperties(cidade, cidadeAtual, "id");
      Cidade cidadeSalva = cidadeService.salvar(cidadeAtual.get());
      return ResponseEntity.ok(cidadeSalva);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{cidadeId}")
  public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
    try {
      cidadeService.excluir(cidadeId);
      return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
