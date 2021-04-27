package com.fema.gruposalunos.controledegrupoparaalunos.controller.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupoUsuario.GrupoUsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private IGrupoService grupoService;

    @PostMapping(value = "/cadastrar", name = "Cadastra um novo grupo")
    public ResponseEntity<?> cadastrarNovoGrupo(@RequestBody @Valid GrupoDTO grupoDTO){
        GrupoDTO grupoSalvo = grupoService.cadastrarNovoGrupo(grupoDTO);
        return ResponseEntity.ok(grupoSalvo);
    }

    @PostMapping(value = "/inserir/usuario/grupo", name = "Insere um usuario no grupo")
    public ResponseEntity<?> inserirUsuarioNoGrupo(@RequestBody @Valid GrupoUsuarioDTO grupoUsuarioDTO){
        grupoService.inserirUsuarioNoGrupo(grupoUsuarioDTO);
        return ResponseEntity.ok(grupoUsuarioDTO);
    }

    @GetMapping(name = "Retorna todos os grupos paginados")
    public ResponseEntity<?> buscarTodosOsGrupos(Pageable pageable){
        Page<GrupoDTO> grupoDTOPage = grupoService.recuperarTodosOsGrupos(pageable);
        return ResponseEntity.ok(grupoDTOPage);
    }


    @GetMapping(value = "/participantes" ,name = "Retorna todos os grupos paginados e com seus integrantes")
    public ResponseEntity<?> buscarTodosOsGruposComIntegrantes(Pageable pageable){
        Page<GrupoDTO> grupoDTOPage = grupoService.recuperarTodosOsGruposComParticipantes(pageable);
        return ResponseEntity.ok(grupoDTOPage);
    }

    @GetMapping(value = "/{id}", name = "Retorna um grupo pelo id")
    public ResponseEntity<?> recuperarGrupoPeloId(@PathVariable Long id){
        GrupoDTO grupo = grupoService.recuperarPeloId(id);
        return ResponseEntity.ok(grupo);
    }

    @PutMapping(value ="/fechar/{id}", name="Fechar grupo pelo ID")
    public ResponseEntity<?> finalizarGrupo(@PathVariable Long id) {
        grupoService.finalizarGrupo(id);
        return ResponseEntity.ok(id);
    }

    @PostMapping(value = "/finalizar/sistema", name = "Finalizar todo o sistema")
    public ResponseEntity<?> finalizarSistema(){
        grupoService.finalizarSistema();
        return ResponseEntity.ok().build();
    }
}
