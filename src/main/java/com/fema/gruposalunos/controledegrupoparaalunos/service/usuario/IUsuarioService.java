package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;


public interface IUsuarioService {

    void cadastrarNovoUsuario(UsuarioDTO usuarioDTO);
    Page<UsuarioDTO> recuperarTodos(Pageable pageable);
    UsuarioDTO recuperarPeloId(String id);
    List<User> recuperarTodosUsuariosPeloIdDoGrupo(String id_grupo);
    void atualizarUsuario(UsuarioDTO usuarioDTO);
    boolean usuarioEAdministrador(UsuarioDTO usuarioDTO);
    UsuarioDTO buscarAdmDoSistema();
    void deletarTodosOsUsuarios();
}
