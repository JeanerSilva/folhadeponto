package com.ilia.ponto.folhadeponto.controller;

import lombok.Getter;

public class ApiErrors {

    @Getter
    private String mensagem;

    public ApiErrors(String mensagem) {
        this.mensagem = mensagem;
    }

}

/*
 @Getter
    private List<String> errors;

    public ApiErrors(List<String> errors) {
        this.errors = errors;
    }

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
*/
