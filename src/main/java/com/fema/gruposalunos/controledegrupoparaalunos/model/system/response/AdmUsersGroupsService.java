package com.fema.gruposalunos.controledegrupoparaalunos.model.system.response;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class AdmUsersGroupsService {

    private final UserResponseDTO admin;
    private final List<UserGroupResponseDTO> groups;

    private AdmUsersGroupsService(UserResponseDTO adm, List<UserGroupResponseDTO> groups) {
        this.admin = adm;
        this.groups = groups;
    }

    public static AdmUsersGroupsService of(UserResponseDTO adm, List<UserGroupResponseDTO> groups) {
        return new AdmUsersGroupsService(adm, groups);
    }
}
