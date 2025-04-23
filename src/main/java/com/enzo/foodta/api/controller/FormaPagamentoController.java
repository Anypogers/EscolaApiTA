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

@RestController
@RequestMapping("/formasPagamento")
public class FormaPagamentoController {
  @Autowired
  private FormaPagamentoRepository formaPagamentoRepository;

  @Autowired
  private FormaPagamentoService formaPagamentoService;

  @GetMapping
  public List<FormaPagamento> listar() {
    return formaPagamentoRepository.listar();
  }

  @GetMapping("/{formaPagamentoId}")
  public ResponseEntity<FormaPagamento> buscar(@PathVariable Long formaPagamentoId){
    FormaPagamento formaPagamento = formaPagamentoRepository.buscar(formaPagamentoId);
    if (formaPagamento != null) {
      return ResponseEntity.ok(formaPagamento);
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
    FormaPagamento formaPagamentoAtual = formaPagamentoRepository.buscar(formaPagamentoId);

    if (formaPagamentoAtual == null) {
      return ResponseEntity.notFound().build();
    }

    BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual, "id");

    formaPagamentoAtual = formaPagamentoService.salvar(formaPagamentoAtual);

    return ResponseEntity.ok(formaPagamentoAtual);
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
