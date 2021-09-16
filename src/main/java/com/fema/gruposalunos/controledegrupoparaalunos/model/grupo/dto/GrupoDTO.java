package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
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

    private String id_grupo;
    @NotNull(message = "{desc.not.null}") @Length(min = 5, message = "{length.cinco}")
    private String descricao;
    @NotNull(message = "{qtd.not.null}") @Positive(message = "{qtd.min.um}")
    private int qtde_participantes;
    @JsonProperty("isOpenGrupo")
    private boolean isOpenGrupo;
    private List<UsuarioDTO> usuariosDTOList;

    public GrupoDTO(Group grupo){
        if(!Objects.isNull(grupo)){
            this.id_grupo = grupo.getId();
            this.descricao = grupo.getDescription();
            this.qtde_participantes = grupo.getNumberParticipants();
            this.isOpenGrupo = grupo.isOpenGroup();
        }
    }

}
