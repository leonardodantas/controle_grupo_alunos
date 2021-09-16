package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupFindtService implements IGroupFindService {

    @Autowired
    private IGroupRepository groupRepository;

    @Autowired
    private IUserFindService userFindService;

    private static final String GROUP_NOT_EXIST = "Grupo não existe";

    @Override
    public Page<GroupResponseDTO> findAllGroup(Pageable pageable) {
        Page<Group> groupPage = groupRepository.findAllGroup(pageable);
        List<GroupResponseDTO> groupsResponseDTO = groupPage.stream().map(GroupResponseDTO::from).collect(Collectors.toUnmodifiableList());
        return new PageImpl<>(groupsResponseDTO, groupPage.getPageable(), groupPage.getTotalElements());
    }

    @Override
    public boolean groupHaveSpace(String idGroup) {
        Group group = verifyGroupExist(idGroup);
        List<User> users = userFindService.findUsersWith(group);
        return users.size() + 1 <= group.getNumberParticipants();
    }

    @Override
    public GroupResponseDTO findById(String id) {
        Group group = verifyGroupExist(id);
        return GroupResponseDTO.from(group);
    }

    @Override
    public List<GroupResponseDTO> findAllGroup() {
        Page<Group> groupPage = groupRepository.findAllGroup();
        return groupPage.stream().map(GroupResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    private Group verifyGroupExist(String id){
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, GROUP_NOT_EXIST));
    }

}
