package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.assembler.GrupoAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class UsuarioAssembler {

    @Autowired
    private GrupoAssembler grupoAssembler;

    public User dtoToEntity(UsuarioDTO usuarioDTO) {

        Group grupo = null;

        if(!Objects.isNull(usuarioDTO.getGrupo())) {
            grupo = grupoAssembler.dtoToEntity(usuarioDTO.getGrupo());
        }
        return User.builder()
                .id(usuarioDTO.getId_usuario())
                .nome(usuarioDTO.getNome())
                .admin(usuarioDTO.getAdmin())
                .email(usuarioDTO.getEmail())
                .grupo(grupo)
                .senha(usuarioDTO.getSenha())
                .perfis(usuarioDTO.getPerfils())
                .build();
    }

    public UsuarioDTO entityToDto(User user) {

        if(Objects.isNull(user)){
            return new UsuarioDTO();
        }
        return UsuarioDTO.builder()
                .id_usuario(user.getId())
                .admin(user.getAdmin())
                .email(user.getEmail())
                .nome(user.getNome())
                .grupo(grupoAssembler.entityToDto(user.getGrupo()))
                .build();

    }

    public List<UsuarioDTO> entityToManyDtos(List<User> users) {
        if(users.isEmpty()){
            return Collections.emptyList();
        }
        users.stream().map(user -> grupoAssembler.entityToDto(user.getGrupo()));
        return users.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }
}
