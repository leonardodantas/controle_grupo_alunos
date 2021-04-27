package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.assembler.GrupoAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
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

    public Usuario dtoToEntity(UsuarioDTO usuarioDTO) {

        Grupo grupo = null;

        if(!Objects.isNull(usuarioDTO.getGrupo())) {
            grupo = grupoAssembler.dtoToEntity(usuarioDTO.getGrupo());
        }
        return Usuario.builder()
                .id(usuarioDTO.getId_usuario())
                .nome(usuarioDTO.getNome())
                .admin(usuarioDTO.getAdmin())
                .email(usuarioDTO.getEmail())
                .grupo(grupo)
                .senha(usuarioDTO.getSenha())
                .perfis(usuarioDTO.getPerfils())
                .build();
    }

    public UsuarioDTO entityToDto(Usuario usuario) {

        if(Objects.isNull(usuario)){
            return new UsuarioDTO();
        }
        return UsuarioDTO.builder()
                .id_usuario(usuario.getId())
                .admin(usuario.getAdmin())
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .grupo(grupoAssembler.entityToDto(usuario.getGrupo()))
                .build();

    }

    public List<UsuarioDTO> entityToManyDtos(List<Usuario> usuarios) {
        if(usuarios.isEmpty()){
            return Collections.emptyList();
        }
        usuarios.stream().map(user -> grupoAssembler.entityToDto(user.getGrupo()));
        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }
}
