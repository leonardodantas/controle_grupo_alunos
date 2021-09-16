package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GroupUserResponseDTO {

    private final GroupResponseDTO group;
    private final List<UserResponseDTO> users;

    private GroupUserResponseDTO(GroupResponseDTO group, List<User> users) {
        this.group = group;
        this.users = users.stream().map(UserResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    public static GroupUserResponseDTO of(GroupResponseDTO group, List<User> users) {
        return new GroupUserResponseDTO(group, users);
    }
}
