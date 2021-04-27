package com.fema.gruposalunos.controledegrupoparaalunos.model.grupo;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;

    @Column(name = "descricao", length = 30)
    private String descricao;

    @Column(name="qtde_participantes", length = 2)
    private Long quantidadeParticipantes;

    @Column(name = "isOpenGrupo")
    private boolean isOpenGrupo;

}
