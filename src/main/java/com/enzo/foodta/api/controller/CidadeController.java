package com.enzo.foodta.api.controller;

import com.enzo.foodta.domain.model.Cidade;
import com.enzo.foodta.domain.repository.CidadeRepository;
import com.enzo.foodta.domain.service.CidadeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private CidadeService cidadeService;

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

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cidade adicionar(@RequestBody Cidade cidade) {
    return cidadeService.salvar(cidade);
  }

  @PutMapping("/{cidadeId}")
  public ResponseEntity<Cidade> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
    Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);

    if (cidadeAtual == null) {
      return ResponseEntity.notFound().build();
    }

    BeanUtils.copyProperties(cidade, cidadeAtual, "id");

    cidadeAtual = cidadeService.salvar(cidadeAtual);

    return ResponseEntity.ok(cidadeAtual);
  }
}
