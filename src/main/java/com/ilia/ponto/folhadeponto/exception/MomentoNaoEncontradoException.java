package com.ilia.ponto.folhadeponto.exception;

public class MomentoNaoEncontradoException extends RuntimeException {

    public MomentoNaoEncontradoException() {
        super("Batida de ponto não encontrada.");
    }
}
