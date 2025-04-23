package com.enzo.foodta.domain.service;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.Cidade;
import com.enzo.foodta.domain.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {
  @Autowired
  private CidadeRepository cidadeRepository;

  public Cidade salvar(Cidade cidade) {
    return cidadeRepository.salvar(cidade);
  }

  public void excluir(Long id) {
    try {
      cidadeRepository.remover(id);
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format(
          "Cidade ou código %d não pode ser removida, pois está em uso.", id
      ));
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(
          "Não existe cadastro de cidade com código %d", id
      ));
    }
  }
}
