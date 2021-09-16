package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;

public interface IUserGroupSaveService {

    void insertUserInGroup(GroupUserRequestDTO groupUserRequestDTO);
}
