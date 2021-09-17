package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;

public interface ISaveUserGroupService {

    void saveUserInGroup(GroupUserRequestDTO groupUserRequestDTO);
}
