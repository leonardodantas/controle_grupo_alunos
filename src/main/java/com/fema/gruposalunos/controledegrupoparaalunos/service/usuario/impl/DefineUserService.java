package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.admin.IFindAdminService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IDefineUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefineUserService implements IDefineUserService {

    @Autowired
    private IFindAdminService findAdminService;

    @Override
    public User defineUser(UsuarioDTO usuarioDTO) {
        if(findAdminService.findAdminExist()){
            return User.from(usuarioDTO).notAdmin();
        }
        return User.from(usuarioDTO).admin();
    }

}
