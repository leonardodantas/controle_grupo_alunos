package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {

    private Long id_grupo;
    @NotNull(message = "{desc.not.null}") @Length(min = 5, message = "{length.cinco}")
    private String descricao;
    @NotNull(message = "{qtd.not.null}") @Positive(message = "{qtd.min.um}")
    private Long qtde_participantes;
    @JsonProperty("isOpenGrupo")
    private boolean isOpenGrupo;
    private List<UsuarioDTO> usuariosDTOList;

    public GrupoDTO(Grupo grupo){
        if(!Objects.isNull(grupo)){
            this.id_grupo = grupo.getId();
            this.descricao = grupo.getDescricao();
            this.qtde_participantes = grupo.getQuantidadeParticipantes();
            this.isOpenGrupo = grupo.isOpenGrupo();
        }
    }

}
