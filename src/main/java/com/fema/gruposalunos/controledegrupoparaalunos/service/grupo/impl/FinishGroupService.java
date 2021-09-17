package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFindGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFinishGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class FinishGroupService implements IFinishGroupService {

    @Autowired
    private IFindGroupService findGroupService;

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public void finishById(String id) {
        GroupResponseDTO groupResponseDTO = findGroupService.findById(id);
        Group group = Group.from(groupResponseDTO).finish();
        groupRepository.save(group);
    }

    @Override
    @Transactional
    public void finallyAll() {
        groupRepository.finallyAllGroups();
    }
}
