package com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserGroupResponseDTO {

    private final String idGroup;
    private final String descripton;
    private final int numberParticipants;
    private final boolean isOpenGroup;
    private final List<UserResponseDTO> users;

    private UserGroupResponseDTO(GroupResponseDTO group, List<User> users) {
        this.idGroup = group.getId();
        this.descripton = group.getDescription();
        this.numberParticipants = group.getNumberParticipants();
        this.isOpenGroup = group.isOpenGroup();
        this.users = users.stream().map(UserResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    public static UserGroupResponseDTO of(GroupResponseDTO group, List<User> users) {
        return new UserGroupResponseDTO(group, users);
    }
}
