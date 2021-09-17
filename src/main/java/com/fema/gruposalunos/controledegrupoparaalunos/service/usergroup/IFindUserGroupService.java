package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFindUserGroupService {

    Page<UserGroupResponseDTO> findAllGroupWithParticipants(Pageable pageable);
    List<UserGroupResponseDTO> findAllGroupWithParticipants();
    List<UserResponseDTO> findAllUsersOfGroup(String groupId);
}
