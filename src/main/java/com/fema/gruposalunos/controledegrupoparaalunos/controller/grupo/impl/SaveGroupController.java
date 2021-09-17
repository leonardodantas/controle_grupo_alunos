package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.ISaveGroupController;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request.GroupRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.ISaveGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/group/create")
public class SaveGroupController implements ISaveGroupController {

    @Autowired
    private ISaveGroupService saveGroupService;

    @Override
    public ResponseEntity<GroupResponseDTO> createGroup(GroupRequestDTO request) {
        GroupResponseDTO response = saveGroupService.save(request);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
