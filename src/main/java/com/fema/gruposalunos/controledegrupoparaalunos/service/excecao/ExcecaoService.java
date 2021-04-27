package com.fema.gruposalunos.controledegrupoparaalunos.service.excecao;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ExcecaoService implements IExcecaoService {

    @Override
    public void lancaExcecao(HttpStatus httpStatus, String mensagem) {
        throw new ResponseStatusException(httpStatus,mensagem);
    }
}
