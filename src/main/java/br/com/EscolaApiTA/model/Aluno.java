package br.com.EscolaApiTA.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

public class Aluno {
  private int id;
  private String nome;

  @ManyToOne
  @JoinColumn(name = "curso_id")
  private int curso_id;

  // Getters and Setters

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public int getCurso_id() {
    return curso_id;
  }

  public void setCurso_id(int curso_id) {
    this.curso_id = curso_id;
  }

  // Equals and Hashcode

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return id == aluno.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
