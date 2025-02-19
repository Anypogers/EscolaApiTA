package com.enzo.foodta.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name="kitchen") // Table Name
public class Cozinha { // Class Name
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name="nome_cozinha", length = 50)
  private String nome;
}