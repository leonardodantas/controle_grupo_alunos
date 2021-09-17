package com.fema.gruposalunos.controledegrupoparaalunos.utils;

import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;

import java.util.List;
import java.util.Optional;

public class PerfilsUtils {

    private static final String ROLE_ALUNO = "ROLE_ALUNO";
    private static final String ADMIN = "S";
    private static final String NOT_ADMIN = "N";

    private PerfilsUtils(){}

    public static String isAdmin(List<Perfil> perfils) {
        Optional<Perfil> perfil = perfils.stream().filter(p -> p.getNome().equals(ROLE_ALUNO)).findFirst();
        if(perfil.isPresent()) {
            return NOT_ADMIN;
        }
        return ADMIN;
    }
}
