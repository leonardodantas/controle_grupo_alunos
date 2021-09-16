package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGroupFindService {

    Page<GroupResponseDTO> findAllGroup(Pageable pageable);
    boolean groupHaveSpace(String idGroup);
    GroupResponseDTO findById(String id);
    List<GroupResponseDTO> findAllGroup();

}
