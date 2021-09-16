package com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Group;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.GroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public class GroupRepositoryImpl implements IGroupRepository {

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public Group save(Group group) {
        try {
            return groupRepository.save(group);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Page<Group> findAllGroup(Pageable pageable) {
        try {
            return groupRepository.findAll(pageable);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    @Override
    public Optional<Group> findById(String idGroup) {
        try {
            return groupRepository.findById(idGroup);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
            groupRepository.deleteAll();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public Page<Group> findAllGroup() {
        try {
            return findAllGroup();
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void finallyAllGroups() {
        groupRepository.finallyAllGroups();
    }
}
