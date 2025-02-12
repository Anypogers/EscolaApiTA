package br.com.EscolaApiTA.controller;

import br.com.EscolaApiTA.model.Aluno;
import br.com.EscolaApiTA.repositories.AlunoRepository;
import br.com.EscolaApiTA.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
  @Autowired
  private AlunoService alunoService;
  @Autowired
  private AlunoRepository alunoRepository;
  @GetMapping("/todas")
  public List<Aluno> listarTodasAluno(){
    return alunoRepository.findAll(Sort.by("nome").ascending());
  }
  @GetMapping("/{id}")
  public ResponseEntity<Aluno> buscarPeloCodigo(@PathVariable int id){
    Optional<Aluno> aluno = alunoRepository.findById(id);
    return aluno.isPresent() ? ResponseEntity.ok(aluno.get()) : ResponseEntity.notFound().build();
  }
  @PostMapping()
  public ResponseEntity<Aluno> inserir(@RequestBody Aluno aluno) {
    Aluno alunoSalva = alunoService.salvar(aluno);
    return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalva);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable int id){
    alunoRepository.deleteById(id);
  }
}