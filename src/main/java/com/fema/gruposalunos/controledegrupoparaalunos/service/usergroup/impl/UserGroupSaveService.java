package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupSaveService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserSaveService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserFindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserGroupSaveService implements IUserGroupSaveService {

    @Autowired
    private IGroupFindService groupSelectService;

    @Autowired
    private IUserFindService userSelectService;

    @Autowired
    private IUserSaveService userSaveService;

    private static final String USER_IS_ADMIN = "Usuario é adminitrador";
    private static final String GROUP_NOT_HAVE_SPACE = "Grupo não possui espaço para novos integrantes";

    @Override
    public void insertUserInGroup(GroupUserRequestDTO groupUserRequestDTO) {
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
        boolean groupHaveSpace = groupSelectService.groupHaveSpace(groupUserRequestDTO.getIdGroup());
        if(!groupHaveSpace) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, GROUP_NOT_HAVE_SPACE);
        }
    }
}
