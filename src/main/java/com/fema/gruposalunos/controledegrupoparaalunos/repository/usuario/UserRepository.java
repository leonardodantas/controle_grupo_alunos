package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByAdmin(String admin);

    Optional<User> findByEmail(String email);

    @Query( value = "SELECT * FROM USUARIOS WHERE EMAIL = :email AND SENHA = :senha", nativeQuery = true)
    User login(@Param("email") String email, @Param("email") String senha);

    List<User> findAllById(String id);

    @Query(value = "SELECT * FROM USUARIOS WHERE ID_USUARIO = :id AND ADMIN = 'S'", nativeQuery = true)
    Optional<User> buscarAdmPeloId(@Param("id") String id_usuario);

    List<User> findAllByGrupo(Group group);
    List<User> findAllByGrupoId(String id);
}
