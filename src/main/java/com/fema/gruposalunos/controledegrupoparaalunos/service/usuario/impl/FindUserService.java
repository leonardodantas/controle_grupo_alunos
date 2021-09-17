package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IFindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindUserService implements IFindUserService {

    @Autowired
    private IUserRepository userRepository;

    private static final String USER_NOT_EXIST = "Usuario não existe";
    private static final String ADMIN = "S";
    private static final String ADMIN_NOT_EXIST = "Usuario Administrador não existe";

    @Override
    public List<User> findUsersWith(Group group) {
        return userRepository.findUsersWith(group);
    }

    @Override
    public UserResponseDTO findUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_EXIST));
        return UserResponseDTO.from(user);
    }

    @Override
    public List<User> findUsersWith(GroupResponseDTO groupDTO) {
        Group group = Group.from(groupDTO);
        return this.findUsersWith(group);
    }

    @Override
    public UserResponseDTO findAdm() {
        User user = userRepository.findByAdmin(ADMIN).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ADMIN_NOT_EXIST));
        return UserResponseDTO.from(user);
    }

    @Override
    public UserResponseDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_EXIST));
        return UserResponseDTO.from(user);
    }

    @Override
    public List<UserResponseDTO> findAll(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.stream().map(UserResponseDTO::from).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public UserResponseDTO findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, USER_NOT_EXIST));;
        return UserResponseDTO.from(user);
    }
}
