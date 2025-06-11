package com.enzo.foodta.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

  @UpdateTimestamp
  @Column(name = "data_atualizacao", columnDefinition = "dateTime")
  private LocalDateTime dataAtualizacao;

  @CreationTimestamp
  @Column(name = "data_cadastro", columnDefinition = "dateTime")
  private LocalDateTime dataCadastro;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cozinha_id")
  private Cozinha cozinha;

  @Embedded
  private Endereco endereco;
}
