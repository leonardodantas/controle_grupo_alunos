package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.IGroupFindController;
import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupFindService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IUserGroupFindService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/group")
public class GroupFindController implements IGroupFindController {

    @Autowired
    private IGroupFindService groupFindService;

    @Autowired
    private IUserGroupFindService userGroupFindService;

    @Override
    public ResponseEntity<GroupResponseDTO> findAll(Pageable pageable) {
        Page<GroupResponseDTO> response = groupFindService.findAllGroup(pageable);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserGroupResponseDTO> findAllWithParticipants(Pageable pageable) {
        Page<UserGroupResponseDTO> response = userGroupFindService.findAllGroupWithParticipants(pageable);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GroupResponseDTO> findById(String id) {
        GroupResponseDTO response = groupFindService.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
