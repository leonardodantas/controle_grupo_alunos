package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.assembler;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GrupoAssembler {

    public Grupo dtoToEntity(GrupoDTO grupoDTO){
        return Grupo.builder()
                .id(grupoDTO.getId_grupo())
                .descricao(grupoDTO.getDescricao())
                .quantidadeParticipantes(grupoDTO.getQtde_participantes())
                .isOpenGrupo(grupoDTO.isOpenGrupo())
                .build();
    }

    public GrupoDTO entityToDto(Grupo grupo){

        if(Objects.isNull(grupo)) {
            return GrupoDTO.builder().build();
        }
        return GrupoDTO.builder()
                .id_grupo(grupo.getId())
                .descricao(grupo.getDescricao())
                .qtde_participantes(grupo.getQuantidadeParticipantes())
                .isOpenGrupo(grupo.isOpenGrupo())
                .build();
    }

    public List<GrupoDTO> entitysToManyDtos(List<Grupo> grupos){
        return grupos.stream().map(GrupoDTO::new).collect(Collectors.toList());
    }
}
