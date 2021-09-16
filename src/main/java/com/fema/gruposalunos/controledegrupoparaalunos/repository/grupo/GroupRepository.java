package com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface GroupRepository extends JpaRepository<Group, String> {

    @Query(value = "SELECT COUNT(*) FROM USUARIOS WHERE GRUPO_ID = :id", nativeQuery = true)
    int quantidadeAtualDeIntegrantesDoGrupo(@Param("id") String id_grupo);
    @Query(value = "UPDATE GRUPO SET isOpenGrupo = FALSE", nativeQuery = true)
    void finalizarTodosOsGrupos();

    @Query(value = "UPDATE GRUPO SET isOpenGrupo = FALSE", nativeQuery = true)
    void finallyAllGroups();
}

