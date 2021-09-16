package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request.GroupRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;

public interface IGroupSaveService {

    GroupResponseDTO saveGroup(GroupRequestDTO groupDTO);
}
