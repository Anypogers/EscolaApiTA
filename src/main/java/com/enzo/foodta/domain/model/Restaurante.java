package com.enzo.foodta.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_restaurante")
public class Restaurante {
  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  @Column(name = "taxa_frete")
  private BigDecimal taxaFrete;

  @ManyToOne
  @JoinColumn(name = "cozinha_id")
  private Cozinha cozinha;
}
