package com.fema.gruposalunos.controledegrupoparaalunos.model.email;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDTO {

    String emailDestinatario;
    String nomeDestino;
    String senha;
}
