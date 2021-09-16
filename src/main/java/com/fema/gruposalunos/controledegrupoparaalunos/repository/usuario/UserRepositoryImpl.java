package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

//@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findUsersWith(Group group) {
        try {
            return Collections.emptyList();
//            return userRepository.findAllByGroup(group);
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
    public Optional<User> findByadmin(String admin) {
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
}
