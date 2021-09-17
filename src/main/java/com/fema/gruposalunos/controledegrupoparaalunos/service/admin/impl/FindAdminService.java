package com.fema.gruposalunos.controledegrupoparaalunos.service.admin.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.admin.IFindAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindAdminService implements IFindAdminService {

    @Autowired
    private IUserRepository userRepository;

    private static final String ADMIN = "S";

    @Override
    public boolean findAdminExist() {
        return userRepository.
                findByAdmin(ADMIN)
                .isPresent();
    }
}
