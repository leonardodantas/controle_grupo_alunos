package com.fema.gruposalunos.controledegrupoparaalunos.controller.autenticacao;

import com.fema.gruposalunos.controledegrupoparaalunos.model.token.dto.TokenDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler.UsuarioAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao.TokenService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Autenticação")
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
        User user = usuarioAssembler.dtoToEntity(usuarioDTO);
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getSenha());

        try {
            Authentication authentication =  authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }}
