package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.assembler;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GrupoAssembler {

    public Group dtoToEntity(GrupoDTO grupoDTO){
        return Group.builder()
                .id(grupoDTO.getId_grupo())
                .description(grupoDTO.getDescricao())
                .numberParticipants(grupoDTO.getQtde_participantes())
                .isOpenGroup(grupoDTO.isOpenGrupo())
                .build();
    }

    public GrupoDTO entityToDto(Group grupo){

        if(Objects.isNull(grupo)) {
            return GrupoDTO.builder().build();
        }
        return GrupoDTO.builder()
                .id_grupo(grupo.getId())
                .descricao(grupo.getDescription())
                .qtde_participantes(grupo.getNumberParticipants())
                .isOpenGrupo(grupo.isOpenGroup())
                .build();
    }

    public List<GrupoDTO> entitysToManyDtos(List<Group> grupos){
        return grupos.stream().map(GrupoDTO::new).collect(Collectors.toList());
    }
}
