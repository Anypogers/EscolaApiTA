package com.enzo.foodta.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class Endereco {
  @ManyToOne
  @JoinColumn(name = "endereco_cidade_id")
  private Cidade cidade;
  @Column(name = "endereco_cep")
  private String cep;
  @Column(name = "endereco_logradouro")
  private String logradouro;
  @Column(name = "endereco_numero")
  private String numero;
  @Column(name = "endereco_complemento")
  private String complemento;
  @Column(name = "endereco_bairro")
  private String bairro;
}
