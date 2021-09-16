package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGroupDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GroupDeleteService implements IGroupDeleteService {

    @Autowired
    private IGroupRepository groupRepository;

    @Override
    @Transactional
    public void deleteAllGroup() {
        groupRepository.deleteAll();
    }
}
