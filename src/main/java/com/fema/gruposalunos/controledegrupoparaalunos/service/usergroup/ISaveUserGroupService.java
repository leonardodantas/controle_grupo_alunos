package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;

public interface ISaveUserGroupService {

    void saveUserInGroup(GroupUserRequestDTO groupUserRequestDTO);
    void saveUserInGroup(User user);
}
