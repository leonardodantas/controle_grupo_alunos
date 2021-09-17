package com.fema.gruposalunos.controledegrupoparaalunos.service.system.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailSendService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IDeleteGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFinishGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.IFinishSystemService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IFindUserGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IDeleteUserService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinishSystemService implements IFinishSystemService {

    @Autowired
    private IFindUserService findUserService;

    @Autowired
    private IFindUserGroupService findUserGroupService;

    @Autowired
    private IEmailSendService emailSendService;

    @Autowired
    private IFinishGroupService finishGroupService;

    @Autowired
    private IDeleteUserService deleteUserService;

    @Autowired
    private IDeleteGroupService deleteGroupService;

    @Override
    public void finishSystem() {
        UserResponseDTO user = findUserService.findAdm();
        List<UserGroupResponseDTO> groups = findUserGroupService.findAllGroupWithParticipants();
        finishGroupService.finallyAll();
        deleteAllUsersAndGroups();
        emailSendService.send(user, groups);
    }

    private void deleteAllUsersAndGroups(){
        deleteUserService.deleteAllUsers();
        deleteGroupService.deleteAll();
    }

}
