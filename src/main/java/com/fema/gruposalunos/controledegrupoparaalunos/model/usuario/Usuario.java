package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;
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
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Long id;

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
    private Grupo grupo;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Perfil> perfis = new ArrayList<>();

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

    public Long getIdGrupo() {
        return grupo.getId();
    }
}
