package com.fema.gruposalunos.controledegrupoparaalunos.service.excecao;

import org.springframework.http.HttpStatus;

public interface IExcecaoService {

    void lancaExcecao(HttpStatus httpStatus, String mensagem);

}
