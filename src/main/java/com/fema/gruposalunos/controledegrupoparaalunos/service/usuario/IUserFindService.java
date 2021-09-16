package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;

import java.util.List;

public interface IUserFindService {

    List<User> findUsersWith(Group group);
    UserResponseDTO findUserById(String id);
    List<User> findUsersWith(GroupResponseDTO group);
    UserResponseDTO findAdm();
}
