package com.enzo.foodta.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException{
  public EntidadeNaoEncontradaException(String message) {
    super(message);
  }
}
