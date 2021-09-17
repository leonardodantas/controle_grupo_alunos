package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IDeleteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteUserService implements IDeleteUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
