package com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = repository.findByEmail(username);
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Dados inválidos!");
        }
        return usuario.get();
    }
}
