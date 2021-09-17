package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFindGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IFindUserGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindUserGroupService implements IFindUserGroupService {

    @Autowired
    private IFindGroupService groupFindService;

    @Autowired
    private IFindUserService userFindService;

    @Override
    public Page<UserGroupResponseDTO> findAllGroupWithParticipants(Pageable pageable) {
        Page<GroupResponseDTO> groups = groupFindService.findAll(pageable);
        List<UserGroupResponseDTO> userGroupResponseDTO = findUsersForGroups(groups);
        return new PageImpl<>(userGroupResponseDTO, groups.getPageable(), groups.getTotalElements());
    }

    @Override
    public List<UserGroupResponseDTO> findAllGroupWithParticipants() {
        List<GroupResponseDTO> groups = groupFindService.findAll();
        return findUsersForGroups(groups);
    }

    @Override
    public List<UserResponseDTO> findAllUsersOfGroup(String groupId) {
        Group group = Group.builder().id(groupId).build();
        List<User> users = userFindService.findUsersWith(group);
        return users.stream().map(UserResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    private List<UserGroupResponseDTO> findUsersForGroups(Page<GroupResponseDTO> groups) {
        List<UserGroupResponseDTO> userGroupResponseDTO = new ArrayList<>();
        populateList(groups.getContent(), userGroupResponseDTO);
        return userGroupResponseDTO;
    }

    private void populateList(List<GroupResponseDTO> groups, List<UserGroupResponseDTO> userGroupResponseDTO) {
        groups.forEach(group -> {
            List<User> users = userFindService.findUsersWith(group);
            userGroupResponseDTO.add(UserGroupResponseDTO.of(group, users));
        });
    }

    private List<UserGroupResponseDTO> findUsersForGroups(List<GroupResponseDTO> groups) {
        List<UserGroupResponseDTO> userGroupResponseDTO = new ArrayList<>();
        populateList(groups, userGroupResponseDTO);
        return userGroupResponseDTO;
    }
}
