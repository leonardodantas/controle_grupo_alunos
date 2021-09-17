package com.fema.gruposalunos.controledegrupoparaalunos.service.system.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.system.response.AdmUsersGroupsService;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.IFindAdmUsersGroupsService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IFindUserGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAdmUsersGroupsService implements IFindAdmUsersGroupsService {

    @Autowired
    private IFindUserService findUserService;

    @Autowired
    private IFindUserGroupService findUserGroupService;

    @Override
    public AdmUsersGroupsService find() {
        UserResponseDTO adm = findUserService.findAdm();
        List<UserGroupResponseDTO> groups = findUserGroupService.findAllGroupWithParticipants();
        return AdmUsersGroupsService.of(adm, groups);
    }
}
