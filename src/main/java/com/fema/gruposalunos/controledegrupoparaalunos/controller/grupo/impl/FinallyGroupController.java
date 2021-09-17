package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo.IFinallyGroupController;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFinishGroupService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Grupos")
@RestController
@RequestMapping("/group")
public class FinallyGroupController implements IFinallyGroupController {

    @Autowired
    private IFinishGroupService finishGroupService;

    @Override
    public ResponseEntity<?> finallyGroup(String id) {
        finishGroupService.finishById(id);
        return ResponseEntity.ok().build();
    }
}
