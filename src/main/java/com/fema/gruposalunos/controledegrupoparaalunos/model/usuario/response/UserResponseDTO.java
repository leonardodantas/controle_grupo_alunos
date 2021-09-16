package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {

    private final String id;
    private final String nome;
    private final String email;
    private final String admin;
    private final GroupResponseDTO grupo;

    private UserResponseDTO(User user) {
        this.id = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.admin = user.getAdmin();
        this.grupo = GroupResponseDTO.from(user.getGrupo());
    }

    public static UserResponseDTO from(User user) {
        return new UserResponseDTO(user);
    }

    public boolean isAdmin(){
        return admin.equals("S");
    }
}
