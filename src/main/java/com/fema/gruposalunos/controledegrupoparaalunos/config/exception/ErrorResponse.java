package com.fema.gruposalunos.controledegrupoparaalunos.config.exception;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String campo;
    private String erro;

    public ErrorResponse(String campo, String erro){
        this.campo = campo;
        this.erro = erro;
    }
}
