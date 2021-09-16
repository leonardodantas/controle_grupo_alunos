package com.fema.gruposalunos.controledegrupoparaalunos.service.email;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;

import java.util.List;

public interface IEmailSendService {
    void send(UserResponseDTO user, List<UserGroupResponseDTO> groups);
}
