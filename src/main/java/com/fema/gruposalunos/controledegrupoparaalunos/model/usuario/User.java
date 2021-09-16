package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private String id;

    @Column(name="nome", length = 50)
    private String nome;

    @Column(name="senha",length = 10)
    private String senha;

    @Column(name="email", length = 50, unique = true)
    private String email;

    @Column(name="admin", length = 1)
    private String admin;

    @OneToOne
    @JoinColumn(name = "grupo_id")
    private Group grupo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

    private User(UserResponseDTO userResponseDTO, GroupResponseDTO group) {
        this.id = userResponseDTO.getId();
        this.grupo = Group.from(group);
    }

    public static User of(UserResponseDTO userResponseDTO, GroupResponseDTO group) {
        return new User(userResponseDTO, group);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getIdGrupo() {
        return grupo.getId();
    }
}
