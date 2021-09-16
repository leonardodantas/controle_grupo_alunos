package com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IGroupRepository {

    Group save(Group group);
    Page<Group> findAllGroup(Pageable pageable);
    Optional<Group> findById(String idGroup);
    void deleteAll();
    Page<Group> findAllGroup();
    void finallyAllGroups();

}
