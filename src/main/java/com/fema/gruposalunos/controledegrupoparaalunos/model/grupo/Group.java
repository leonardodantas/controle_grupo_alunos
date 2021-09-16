package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.request.GroupRequestDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.response.GroupResponseDTO;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="grupo")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private String id;

    @Column(name = "descricao", length = 30)
    private String description;

    @Column(name="qtde_participantes", length = 2)
    private int numberParticipants;

    @Column(name = "isOpenGrupo")
    private boolean isOpenGroup;

    private Group(GroupRequestDTO groupDTO) {
        this.id = UUID.randomUUID().toString();
        this.description = groupDTO.getDescription();
        this.numberParticipants = groupDTO.getNumberParticipants();
        this.isOpenGroup = groupDTO.isOpenGroup();
    }

    private Group(GroupResponseDTO groupDTO) {
        this.id = groupDTO.getId();
        this.description = groupDTO.getDescription();
        this.numberParticipants = groupDTO.getNumberParticipants();
        this.isOpenGroup = groupDTO.isOpenGroup();
    }

    private Group(String id) {
        this.id = id;
        this.isOpenGroup = false;
    }

    public static Group from(GroupRequestDTO groupDTO) {
        return new Group(groupDTO);
    }

    public static Group from(GroupResponseDTO group) {
        return new Group(group);
    }

    public Group finish() {
        return new Group(this.id);
    }
}
