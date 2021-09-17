package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup.IFindUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IDefineUserService defineUserService;

    @Autowired
    private IFindUserService findUserService;

    @Autowired
    private IFindUserGroupService findUserGroupService;

    @Autowired
    private ISaveUserService saveUserService;

    @Autowired
    private IDeleteUserService deleteUserService;

    @Override
    @Transactional
    public void cadastrarNovoUsuario(UsuarioDTO usuarioDTO) {
        User user = defineUserService.defineUser(usuarioDTO);
        existeUsuarioNoBanco(usuarioDTO);
        saveUserService.save(user);
    }

    @Override
    public Page<UserResponseDTO> recuperarTodos(Pageable pageable) {
        List<UserResponseDTO> userResponseDTOS = findUserService.findAll(pageable);
        return new PageImpl<>(userResponseDTOS);
    }

    @Override
    public UserResponseDTO recuperarPeloId(String id) {
        return findUserService.findById(id);
    }

    @Override
    public List<UserResponseDTO> recuperarTodosUsuariosPeloIdDoGrupo(String groupId) {
        return findUserGroupService.findAllUsersOfGroup(groupId);
    }

    @Override
    @Transactional
    public void atualizarUsuario(UsuarioDTO usuarioDTO) {
        saveUserService.updateUser(usuarioDTO);
    }

    @Override
    public boolean usuarioEAdministrador(UsuarioDTO usuarioDTO) {
        UserResponseDTO user = findUserService.findAdm();
        return usuarioDTO.getId_usuario().equals(user.getId());
    }

    @Override
    public UserResponseDTO buscarAdmDoSistema() {
        return findUserService.findAdm();
    }

    @Override
    @Transactional
    public void deletarTodosOsUsuarios() {
        deleteUserService.deleteAllUsers();
    }

    private void existeUsuarioNoBanco(UsuarioDTO usuarioDto){
        findUserService.findUserByEmail(usuarioDto.getEmail());
    }
}
