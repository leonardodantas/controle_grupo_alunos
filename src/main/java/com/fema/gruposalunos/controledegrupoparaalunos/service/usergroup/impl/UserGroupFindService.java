package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupFindService implements IUserGroupFindService {

    @Autowired
    private IGroupFindService groupFindService;

    @Autowired
    private IUserFindService userFindService;

    @Override
    public Page<UserGroupResponseDTO> findAllGroupWithParticipants(Pageable pageable) {
        Page<GroupResponseDTO> groups = groupFindService.findAllGroup(pageable);
        List<UserGroupResponseDTO> userGroupResponseDTO = findUsersForGroups(groups);
        return new PageImpl<>(userGroupResponseDTO, groups.getPageable(), groups.getTotalElements());
    }

    @Override
    public List<UserGroupResponseDTO> findAllGroupWithParticipants() {
        List<GroupResponseDTO> groups = groupFindService.findAllGroup();
        return findUsersForGroups(groups);
    }

    private List<UserGroupResponseDTO> findUsersForGroups(Page<GroupResponseDTO> groups) {
        List<UserGroupResponseDTO> userGroupResponseDTO = new ArrayList<>();
        populateList(groups.getContent(), userGroupResponseDTO);
        return userGroupResponseDTO;
    }

    private void populateList(List<GroupResponseDTO> groups, List<UserGroupResponseDTO> userGroupResponseDTO) {
        groups
                .forEach(group -> {
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
