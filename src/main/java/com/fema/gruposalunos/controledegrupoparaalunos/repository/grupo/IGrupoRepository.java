package com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Long> {

    @Query(value = "SELECT COUNT(*) FROM USUARIOS WHERE GRUPO_ID = :id", nativeQuery = true)
    int quantidadeAtualDeIntegrantesDoGrupo(@Param("id") Long id_grupo);
    @Query(value = "UPDATE GRUPO SET isOpenGrupo = FALSE", nativeQuery = true)
    void finalizarTodosOsGrupos();
}

