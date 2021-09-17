package com.fema.gruposalunos.controledegrupoparaalunos.controller.system.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.controller.system.ISystemController;
import com.fema.gruposalunos.controledegrupoparaalunos.service.system.IFinishSystemService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Sistema")
@RestController
@RequestMapping("/system")
public class SystemController implements ISystemController {

    @Autowired
    private IFinishSystemService systemFinishService;

    @Override
    public ResponseEntity<?> finishSystem() {
        systemFinishService.finishSystem();
        return ResponseEntity.ok().build();
    }
}
