package com.fema.gruposalunos.controledegrupoparaalunos.service.usergroup;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserGroupFindService {

    Page<UserGroupResponseDTO> findAllGroupWithParticipants(Pageable pageable);
    List<UserGroupResponseDTO> findAllGroupWithParticipants();
}
