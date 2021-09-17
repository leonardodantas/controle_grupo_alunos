package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserRepository  {

    List<User> findUsersWith(Group group);
    User save(User user);
    Optional<User> findById(String id);
    Optional<User> findByAdmin(String admin);
    void deleteAll();
    Optional<User> findByEmail(String email);
    Page<User> findAll(Pageable pageable);
}
