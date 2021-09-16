package com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.request;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GroupUserRequestDTO {

    @NotBlank(message = "{id.not.null}")
    private String idGroup;
    @NotBlank(message = "{id.not.null}")
    private String idUser;
}
