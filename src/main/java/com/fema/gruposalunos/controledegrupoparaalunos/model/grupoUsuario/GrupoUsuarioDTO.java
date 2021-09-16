package com.fema.gruposalunos.controledegrupoparaalunos.model.grupoUsuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoUsuarioDTO {

    @NotNull(message = "{id.not.null}") @Positive(message = "{id.positive}")
    private String id_grupo;
    @NotNull(message = "{id.not.null}") @Positive(message = "{id.positive}")
    private String id_usuario;
}
