package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.FormaPagamento;
import com.enzo.foodta.domain.repository.FormaPagamentoRepository;
import com.enzo.foodta.domain.service.FormaPagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formapagamentos")
public class FormaPagamentoController {
  @Autowired
  private FormaPagamentoRepository formaPagamentoRepository;

  @Autowired
  private FormaPagamentoService formaPagamentoService;

  @GetMapping
  public List<FormaPagamento> listar() {
    return formaPagamentoRepository.findAll();
  }

  @GetMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId){
    Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(formaPagamentoId);
    if (formaPagamento.isPresent()) {
      return ResponseEntity.ok(formaPagamento.get());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FormaPagamento adicionar(@RequestBody FormaPagamento formaPagamento) {
    return formaPagamentoService.salvar(formaPagamento);
  }

  @PutMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long formaPagamentoId, @RequestBody FormaPagamento formaPagamento) {
    Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(formaPagamentoId);
    if (formaPagamentoAtual.isPresent()) {
      BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");
      FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamentoAtual.get());
      return ResponseEntity.ok(formaPagamentoSalva);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamento> remover(@PathVariable Long formaPagamentoId) {
    try {
      formaPagamentoService.excluir(formaPagamentoId);
      return ResponseEntity.notFound().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.notFound().build();
    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }
}
