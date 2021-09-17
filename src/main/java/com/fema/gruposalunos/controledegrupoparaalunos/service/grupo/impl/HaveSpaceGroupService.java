package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFindGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IHaveSpaceGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HaveSpaceGroupService implements IHaveSpaceGroupService {

    @Autowired
    private IFindGroupService findGroupService;

    @Autowired
    private IFindUserService findUserService;

    @Override
    public boolean groupHaveSpace(String idGroup) {
        GroupResponseDTO groupResponseDTO = findGroupService.findById(idGroup);
        List<User> users = findUserService.findUsersWith(groupResponseDTO);
        return users.size() + 1 <= groupResponseDTO.getNumberParticipants();
    }
}
