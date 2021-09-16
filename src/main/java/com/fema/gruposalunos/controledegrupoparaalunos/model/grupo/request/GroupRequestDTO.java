package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request;

import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class GroupRequestDTO {

    @NotNull(message = "{desc.not.null}") @Length(min = 5, message = "{length.cinco}")
    private String description;
    @NotNull(message = "{qtd.not.null}") @Positive(message = "{qtd.min.um}")
    private int numberParticipants;
    private boolean openGroup;
}
