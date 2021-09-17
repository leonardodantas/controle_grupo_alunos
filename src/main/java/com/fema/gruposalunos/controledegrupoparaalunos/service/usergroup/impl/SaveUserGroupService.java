package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFindGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IHaveSpaceGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.ISaveUserGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.ISaveUserService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class SaveUserGroupService implements ISaveUserGroupService {

    @Autowired
    private IFindGroupService groupSelectService;

    @Autowired
    private IFindUserService userSelectService;

    @Autowired
    private ISaveUserService userSaveService;

    @Autowired
    private IHaveSpaceGroupService groupHaveSpaceService;

    private static final String USER_IS_ADMIN = "Usuario é adminitrador";
    private static final String GROUP_NOT_HAVE_SPACE = "Grupo não possui espaço para novos integrantes";

    @Override
    public void saveUserInGroup(GroupUserRequestDTO groupUserRequestDTO) {
        verifyGroupHaveSpace(groupUserRequestDTO);
        UserResponseDTO userResponseDTO = getUserWhereNotAdmin(groupUserRequestDTO);
        GroupResponseDTO group = groupSelectService.findById(groupUserRequestDTO.getIdGroup());
        User user = User.of(userResponseDTO, group);
        userSaveService.updateUser(user);
    }

    private UserResponseDTO getUserWhereNotAdmin(GroupUserRequestDTO groupUserRequestDTO) {
        UserResponseDTO userResponseDTO = userSelectService.findUserById(groupUserRequestDTO.getIdUser());
        if(userResponseDTO.isAdmin()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_IS_ADMIN);
        }
        return userResponseDTO;
    }

    private void verifyGroupHaveSpace(GroupUserRequestDTO groupUserRequestDTO) {
        boolean groupHaveSpace = groupHaveSpaceService.groupHaveSpace(groupUserRequestDTO.getIdGroup());
        if(!groupHaveSpace) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GROUP_NOT_HAVE_SPACE);
        }
    }
}
