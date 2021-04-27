package com.fema.gruposalunos.controledegrupoparaalunos.model.token.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class TokenDTO {

    private String token;
    private String tipo;

}
