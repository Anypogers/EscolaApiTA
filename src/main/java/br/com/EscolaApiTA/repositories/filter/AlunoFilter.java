package br.com.EscolaApiTA.repositories.filter;

public class AlunoFilter {
  private String nome;

  private int curso_id;

  // Getters and Setters

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
}
