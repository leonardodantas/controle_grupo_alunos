package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.IGroupUpdateController;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupUpdateService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/group")
public class GroupUpdateController implements IGroupUpdateController {

    @Autowired
    private IGroupUpdateService groupUpdateService;

    @Override
    public ResponseEntity<?> finallyGroup(String id) {
        groupUpdateService.finishGroup(id);
        return ResponseEntity.ok().build();
    }
}
