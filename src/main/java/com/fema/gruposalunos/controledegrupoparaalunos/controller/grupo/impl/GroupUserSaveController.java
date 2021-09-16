package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.IGroupUserSaveController;
import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request.GroupUserRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupSaveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/group/user/insert")
public class GroupUserSaveController implements IGroupUserSaveController {

    @Autowired
    private IUserGroupSaveService userGroupSaveService;

    @Override
    public ResponseEntity<GroupUserRequestDTO> insertUserGroup(GroupUserRequestDTO request) {
        userGroupSaveService.insertUserInGroup(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}
