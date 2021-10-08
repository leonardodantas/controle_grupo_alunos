package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler.UsuarioAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGrupoRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUsuarioRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.*;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl.MessageEmailUser;
import com.fema.gruposalunos.controledegrupoparaalunos.service.excecao.IExcecaoService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IGrupoService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.senha.GeradorDeSenha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private IGrupoRepository grupoRepository;

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    @Autowired
    private IGrupoService grupoService;

    @Autowired
    private GeradorDeSenha geradorDeSenha;

    @Autowired
    private IExcecaoService excecaoService;

    @Autowired
    private ISendEmailService sendEmailService;

    @Override
    @Transactional
    public void cadastrarNovoUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = prepararUsuario(usuarioDTO);
        existeUsuarioNoBanco(usuarioDTO);
        inserirUsuarioNoGrupo(usuario);
    }

    public Usuario prepararUsuario(UsuarioDTO usuarioDTO){
        usuarioDTO = definirStatusDoUsuario(usuarioDTO);
        Usuario usuario = usuarioAssembler.dtoToEntity(usuarioDTO);
        usuario.setSenha(gerarSenhaParaUsuario());
        enviarEmailParaUsuarioComSenha(usuario);
        return usuario;
    }

    @Transactional
    public Usuario inserirUsuarioNoGrupo(Usuario usuario) {

        if(grupoService.verificarSeGrupoAceitaIntegrate(usuario.getIdGrupo())){
            try {
                usuarioRepository.save(usuario);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return usuario;
    }

    @Override
    public Page<UsuarioDTO> recuperarTodos(Pageable pageable) {
        Page<Usuario> usuarios = buscarUsuariosNoBanco(pageable);
        List<UsuarioDTO> usuarioDTOList = usuarioAssembler.entityToManyDtos(usuarios.getContent());
        Page<UsuarioDTO> usuarioDTOS = new PageImpl<>(usuarioDTOList);
        return usuarioDTOS;
    }

    @Override
    public UsuarioDTO recuperarPeloId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            excecaoService.lancaExcecao(HttpStatus.NOT_FOUND, "Usuario inexistente");
        }
        return usuarioAssembler.entityToDto(usuario.get());
    }

    @Override
    public List<Usuario> recuperarTodosUsuariosPeloIdDoGrupo(Long id_grupo) {
        Optional<List<Usuario>> allByGrupoId = usuarioRepository.findAllByGrupoId(id_grupo);
        if(allByGrupoId.isPresent()){
            return allByGrupoId.get();
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void atualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioAssembler.dtoToEntity(usuarioDTO);
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean usuarioEAdministrador(UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.buscarAdmPeloId(usuarioDTO.getId_usuario());
        return usuario.isPresent();
    }

    @Override
    public UsuarioDTO buscarAdmDoSistema() {
        if(!verificarSeExisteAdministrador()){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT, "Usuario Administrador não existe");
        }
        Optional<Usuario> userAdmin = usuarioRepository.findByAdmin("S");
        UsuarioDTO usuarioDTO = usuarioAssembler.entityToDto(userAdmin.get());
        return usuarioDTO;
    }

    @Override
    @Transactional
    public void deletarTodosOsUsuarios() {
        usuarioRepository.deleteAll();
    }

    private Page<Usuario> buscarUsuariosNoBanco(Pageable pageable){
        Page<Usuario> usuarios = null;
        try {
            usuarios = usuarioRepository.findAll(pageable);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuarios;
    }

    private void enviarEmailParaUsuarioComSenha(Usuario usuario){
        IMessageEmail message = new MessageEmailUser.Builder(usuario).body(usuario).build();
        sendEmailService.sendEmail(message);
    }

    private UsuarioDTO definirStatusDoUsuario(UsuarioDTO usuarioDTO){
        List<Perfil> perfils = new ArrayList<>();
        if(verificarSeExisteAdministrador()){
            usuarioDTO.setAdmin("N");
            perfils.add(new Perfil(1l,"ROLE_ALUNO"));
        } else {
            usuarioDTO.setAdmin("S");
            perfils.add(new Perfil(2l,"ROLE_PROFESSOR"));
        }
        usuarioDTO.setPerfils(perfils);
        return usuarioDTO;
    }

    private String gerarSenhaParaUsuario(){
        return GeradorDeSenha.getRandomPass();
    }

    private boolean verificarSeExisteAdministrador() {
        Optional<Usuario> usuarioAdm = usuarioRepository.findByAdmin("S");
        return usuarioAdm.isPresent();
    }

    private void existeUsuarioNoBanco(UsuarioDTO usuarioDto){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioDto.getEmail());
        if(usuario.isPresent()){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT,"Usuario já cadastrado");
        }
    }

}
