package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request.GroupRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.ISaveGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveGroupService implements ISaveGroupService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    public GroupResponseDTO save(GroupRequestDTO groupDTO) {
        Group group = Group.from(groupDTO);
        Group groupSave = groupRepository.save(group);
        return GroupResponseDTO.from(groupSave);
    }
}
