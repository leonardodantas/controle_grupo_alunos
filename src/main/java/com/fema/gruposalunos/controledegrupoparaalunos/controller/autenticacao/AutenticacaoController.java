package com.fema.gruposalunos.controledegrupoparaalunos.controller.autenticacao;

import com.fema.gruposalunos.controledegrupoparaalunos.model.token.dto.TokenDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler.UsuarioAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioAssembler usuarioAssembler;
    @PostMapping
    public ResponseEntity<TokenDTO> autenticar(@RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioAssembler.dtoToEntity(usuarioDTO);
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha());

        try {
            Authentication authentication =  authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }}
