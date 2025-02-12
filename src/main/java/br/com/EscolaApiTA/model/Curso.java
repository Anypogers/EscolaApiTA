package br.com.EscolaApiTA.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "curso")
public class Curso {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String nome_curso;
  @JsonIgnore
  @OneToMany(mappedBy = "curso")
  private List<Aluno> alunoLista = new ArrayList<>();

  // Getters and Setters
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNome_curso() {
    return nome_curso;
  }

  public void setNome_curso(String nome_curso) {
    this.nome_curso = nome_curso;
  }

  // Equals and Hashcode


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Curso curso = (Curso) o;
    return id == curso.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
