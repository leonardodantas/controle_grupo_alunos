package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository  {

    List<User> findUsersWith(Group group);
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByadmin(String admin);
    void deleteAll();

}
