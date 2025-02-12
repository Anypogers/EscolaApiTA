package br.com.EscolaApiTA.controller;

import br.com.EscolaApiTA.model.Curso;
import br.com.EscolaApiTA.repositories.CursoRepository;
import br.com.EscolaApiTA.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/curso")
public class CursoController {
  @Autowired
  private CursoService cursoService;
  @Autowired
  private CursoRepository cursoRepository;
  @GetMapping("/todas")
  public List<Curso> listarTodasCurso(){
    return cursoRepository.findAll(Sort.by("nome_curso").ascending());
  }
  @GetMapping("/{id}")
  public ResponseEntity<Curso> buscarPeloCodigo(@PathVariable int id){
    Optional<Curso> curso = cursoRepository.findById(id);
    return curso.isPresent() ? ResponseEntity.ok(curso.get()) : ResponseEntity.notFound().build();
  }
  @PostMapping()
  public ResponseEntity<Curso> inserir(@RequestBody Curso curso) {
    Curso cursoSalva = cursoService.salvar(curso);
    return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalva);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void remover(@PathVariable int id){
    cursoRepository.deleteById(id);
  }
}