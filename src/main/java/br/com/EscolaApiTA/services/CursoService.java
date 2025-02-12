package br.com.EscolaApiTA.services;

import br.com.EscolaApiTA.model.Curso;
import br.com.EscolaApiTA.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
  @Autowired
  private CursoRepository cursoRepository;
  public Curso salvar(Curso curso){
    return cursoRepository.save(curso);
  }
}