package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import lombok.Getter;

@Getter
public class GroupResponseDTO {

    private final String id;
    private final String description;
    private final int numberParticipants;
    private final boolean isOpenGroup;

    private GroupResponseDTO(Group group) {
        this.id = group.getId();
        this.description = group.getDescription();
        this.numberParticipants = group.getNumberParticipants();
        this.isOpenGroup = group.isOpenGroup();
    }

    public static GroupResponseDTO from(Group group) {
        return new GroupResponseDTO(group);
    }
}
