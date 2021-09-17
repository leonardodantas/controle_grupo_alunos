package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;

public interface ISaveUserService {
    void updateUser(User user);
    void updateUser(UsuarioDTO usuarioDTO);
    void save(User user);
}
