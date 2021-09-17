package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IFindGroupService {

    Page<GroupResponseDTO> findAll(Pageable pageable);
    GroupResponseDTO findById(String id);
    List<GroupResponseDTO> findAll();

}
