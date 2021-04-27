package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupoUsuario.GrupoUsuarioDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGrupoService {

    GrupoDTO cadastrarNovoGrupo(GrupoDTO grupoDTO);
    Page<GrupoDTO> recuperarTodosOsGrupos(Pageable pageable);
    boolean verificarSeGrupoAceitaIntegrate(Long codigo);
    GrupoDTO recuperarPeloId(Long id);
    void inserirUsuarioNoGrupo(GrupoUsuarioDTO grupoUsuarioDTO);
    Page<GrupoDTO> recuperarTodosOsGruposComParticipantes(Pageable pageable);
    void finalizarGrupo(Long id);
    List<GrupoDTO> recuperarTodosOsGrupoComParticipantesSemPaginacao();
    void finalizarSistema();
}
