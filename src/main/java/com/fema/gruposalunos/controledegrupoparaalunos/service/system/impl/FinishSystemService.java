package com.fema.gruposalunos.controledegrupoparaalunos.service.system.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.system.response.AdmUsersGroupsService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailSendService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFinishGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.IFindAdmUsersGroupsService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.IFinishSystemService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IDeleteUsersGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinishSystemService implements IFinishSystemService {

    @Autowired
    private IFindAdmUsersGroupsService findAdmUsersGroupsService;

    @Autowired
    private IEmailSendService emailSendService;

    @Autowired
    private IFinishGroupService finishGroupService;

    @Autowired
    private IDeleteUsersGroupsService deleteUsersGroupsService;

    @Override
    public void finishSystem() {
        AdmUsersGroupsService admUsersGroupsService = findAdmUsersGroupsService.find();
        finishGroupService.finallyAll();
        deleteUsersGroupsService.deleteAllUsersAndGroups();
        emailSendService.send(admUsersGroupsService.getAdmin(), admUsersGroupsService.getGroups());
    }

}
