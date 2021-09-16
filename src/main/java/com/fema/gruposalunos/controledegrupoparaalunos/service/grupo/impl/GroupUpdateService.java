package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailSendService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupDeleteService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupUpdateService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GroupUpdateService implements IGroupUpdateService {

    @Autowired
    private IGroupFindService groupFindService;

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IUserFindService userFindService;

    @Autowired
    private IUserGroupFindService userGroupSelectService;

    @Autowired
    private IUserDeleteService userDeleteService;

    @Autowired
    private IGroupDeleteService groupDeleteService;

    @Autowired
    private IEmailSendService emailSendService;

    @Override
    public void finishGroup(String id) {
        GroupResponseDTO groupResponseDTO = groupFindService.findById(id);
        Group group = Group.from(groupResponseDTO).finish();
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void finallyAllGroups() {
        groupRepository.finallyAllGroups();
    }
}
