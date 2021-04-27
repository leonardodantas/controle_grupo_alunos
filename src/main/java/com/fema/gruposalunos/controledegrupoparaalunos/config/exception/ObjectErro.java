package com.fema.gruposalunos.controledegrupoparaalunos.config.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ObjectErro {

    private final String message;
    private final String field;
    private final Object parameter;
}
