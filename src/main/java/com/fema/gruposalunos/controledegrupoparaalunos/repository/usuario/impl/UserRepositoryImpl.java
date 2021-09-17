package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUsersWith(Group group) {
        try {
            return userRepository.findAllByGrupo(group);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByAdmin(String admin) {
        return userRepository.findByAdmin(admin);
    }

    @Override
    public void deleteAll() {
        try {
            userRepository.deleteAll();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
