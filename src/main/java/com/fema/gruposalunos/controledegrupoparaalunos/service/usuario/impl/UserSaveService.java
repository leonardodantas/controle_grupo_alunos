package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserSaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserSaveService implements IUserSaveService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void updateUser(User user) {
        try {
            userRepository.save(user);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
