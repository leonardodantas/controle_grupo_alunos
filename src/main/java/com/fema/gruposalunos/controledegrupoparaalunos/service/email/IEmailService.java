package com.fema.gruposalunos.controledegrupoparaalunos.service.email;

import com.fema.gruposalunos.controledegrupoparaalunos.model.email.EmailDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;

import java.util.List;

public interface IEmailService {

    void enviarEmailComSenha(EmailDTO emailDTO);
    void enviarGruposParaEmailDoAdministrador(List<GrupoDTO> grupoDTOList, UsuarioDTO admin);
}
