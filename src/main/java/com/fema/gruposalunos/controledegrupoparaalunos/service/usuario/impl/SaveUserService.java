package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.ISaveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveUserService implements ISaveUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(UsuarioDTO usuarioDTO) {
        User user = User.from(usuarioDTO);
        userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

}
