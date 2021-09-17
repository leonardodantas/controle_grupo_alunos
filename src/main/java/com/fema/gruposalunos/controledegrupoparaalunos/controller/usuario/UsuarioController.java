package com.fema.gruposalunos.controledegrupoparaalunos.controller.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUsuarioService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Usuarios")
@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PostMapping(value ="/cadastrar", name="Cadastrar novo usuario")
    public ResponseEntity<?> inserirUsuario(@RequestBody @Valid UsuarioDTO usuarioDTO) {
        usuarioService.cadastrarNovoUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioDTO);
    }

    @GetMapping(name="Recuperar todos os usuarios")
    public ResponseEntity<?> recureparTodos(Pageable pageable){
        Page<UserResponseDTO> usuarios = usuarioService.recuperarTodos(pageable);
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping(value ="/{id}", name="Recuperar usuario pela ID")
    public ResponseEntity<?> recureparPeloId(@PathVariable String id){
        UserResponseDTO response = usuarioService.recuperarPeloId(id);
        return ResponseEntity.ok(response);
    }

}
