package com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByAdmin(String admin);

    Optional<Usuario> findByEmail(String email);

    @Query( value = "SELECT * FROM USUARIOS WHERE EMAIL = :email AND SENHA = :senha", nativeQuery = true)
    Usuario login(@Param("email") String email, @Param("email") String senha);

    Optional<List<Usuario>> findAllByGrupoId(Long id);

    @Query(value = "SELECT * FROM USUARIOS WHERE ID_USUARIO = :id AND ADMIN = 'S'", nativeQuery = true)
    Optional<Usuario> buscarAdmPeloId(@Param("id") Long id_usuario);
}
