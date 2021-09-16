package com.fema.gruposalunos.controledegrupoparaalunos.service.system.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailSendService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupDeleteService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupUpdateService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.ISystemFinishService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserDeleteService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemFinishService implements ISystemFinishService {

    @Autowired
    private IUserFindService userFindService;

    @Autowired
    private IUserGroupFindService userGroupFindService;

    @Autowired
    private IEmailSendService emailSendService;

    @Autowired
    private IGroupUpdateService groupUpdateService;

    @Autowired
    private IUserDeleteService userDeleteService;

    @Autowired
    private IGroupDeleteService groupDeleteService;

    @Override
    public void finishSystem() {
        UserResponseDTO user = userFindService.findAdm();
        List<UserGroupResponseDTO> groups = userGroupFindService.findAllGroupWithParticipants();
        groupUpdateService.finallyAllGroups();
        deleteAllUsersAndGroups();
        emailSendService.send(user, groups);
    }

    private void deleteAllUsersAndGroups(){
        userDeleteService.deleteAllUsers();
        groupDeleteService.deleteAllGroup();
    }

}
