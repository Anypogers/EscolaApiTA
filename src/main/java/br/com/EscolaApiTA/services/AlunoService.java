package br.com.EscolaApiTA.services;

import br.com.EscolaApiTA.model.Aluno;
import br.com.EscolaApiTA.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
  @Autowired
  private AlunoRepository alunoRepository;
  public Aluno salvar(Aluno aluno){
    return alunoRepository.save(aluno);
  }
}