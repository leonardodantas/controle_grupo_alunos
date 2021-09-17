package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IUsuarioService {

    void cadastrarNovoUsuario(UsuarioDTO usuarioDTO);
    Page<UserResponseDTO> recuperarTodos(Pageable pageable);
    UserResponseDTO recuperarPeloId(String id);
    List<UserResponseDTO> recuperarTodosUsuariosPeloIdDoGrupo(String id_grupo);
    void atualizarUsuario(UsuarioDTO usuarioDTO);
    boolean usuarioEAdministrador(UsuarioDTO usuarioDTO);
    UserResponseDTO buscarAdmDoSistema();
    void deletarTodosOsUsuarios();
}
