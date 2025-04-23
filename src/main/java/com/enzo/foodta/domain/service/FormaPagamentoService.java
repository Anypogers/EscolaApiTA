package com.enzo.foodta.domain.service;

import com.enzo.foodta.domain.exception.EntidadeEmUsoException;
import com.enzo.foodta.domain.exception.EntidadeNaoEncontradaException;
import com.enzo.foodta.domain.model.FormaPagamento;
import com.enzo.foodta.domain.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class FormaPagamentoService {
  @Autowired
  private FormaPagamentoRepository formaPagamentoRepository;

  public FormaPagamento salvar(FormaPagamento formaPagamento) {
    return formaPagamentoRepository.salvar(formaPagamento);
  }

  public void excluir(Long id) {
    try {
      formaPagamentoRepository.remover(id);
    } catch (DataIntegrityViolationException e) {
      throw new EntidadeEmUsoException(String.format(
          "Forma pagamento ou código %d não pode ser removida, pois está em uso.", id
      ));
    } catch (EmptyResultDataAccessException e) {
      throw new EntidadeNaoEncontradaException(String.format(
          "Não existe cadastro de forma pagamento com código %d", id
      ));
    }
  }
}
