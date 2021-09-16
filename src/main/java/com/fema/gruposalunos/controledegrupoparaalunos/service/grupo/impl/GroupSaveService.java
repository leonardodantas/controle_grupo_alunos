package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request.GroupRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupSaveService implements IGroupSaveService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public GroupResponseDTO saveGroup(GroupRequestDTO groupDTO) {
        Group group = Group.from(groupDTO);
        Group groupSave = groupRepository.save(group);
        return GroupResponseDTO.from(groupSave);
    }
}
