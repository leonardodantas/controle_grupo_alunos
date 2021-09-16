package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUserDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDeleteService implements IUserDeleteService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }
}
