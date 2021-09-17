package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IDeleteGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IDeleteUsersGroupsService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IDeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUsersGroupsService implements IDeleteUsersGroupsService {

    @Autowired
    private IDeleteUserService deleteUserService;

    @Autowired
    private IDeleteGroupService deleteGroupService;

    @Override
    public void deleteAllUsersAndGroups() {
        deleteUserService.deleteAllUsers();
        deleteGroupService.deleteAll();
    }
}
