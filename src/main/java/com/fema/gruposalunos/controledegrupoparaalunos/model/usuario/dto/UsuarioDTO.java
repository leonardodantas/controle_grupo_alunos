package com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String id_usuario;
    @NotNull(message = "{name.not.null}") @Length(min = 10, message = "{length.dez}")
    private String nome;
    private String senha;
    @Email @NotNull(message = "{email.not.null}") @Length(min = 10, message = "{length.dez}")
    private String email;
    private String admin;
    private GrupoDTO grupo;
    List<Perfil> perfils = new ArrayList<>();

    public UsuarioDTO(User user){
        this.id_usuario = user.getId();
        this.nome = user.getNome();
        this.email = user.getEmail();
        this.senha = user.getSenha();
        this.admin = user.getAdmin();
        this.grupo = new GrupoDTO(user.getGrupo());
    }

}
