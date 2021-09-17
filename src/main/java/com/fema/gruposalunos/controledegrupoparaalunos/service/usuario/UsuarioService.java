package com.fema.gruposalunos.controledegrupoparaalunos.service.usuario;

import com.fema.gruposalunos.controledegrupoparaalunos.model.email.EmailDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.perfil.Perfil;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler.UsuarioAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.GroupRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.UserRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.excecao.IExcecaoService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IFindGroupService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.grupo.IHaveSpaceGroupService;
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
    private UserRepository usuarioRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    @Autowired
    private GeradorDeSenha geradorDeSenha;

    @Autowired
    private IExcecaoService excecaoService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private IFindGroupService groupFindService;

    @Autowired
    private IHaveSpaceGroupService groupHaveSpaceService;

    @Override
    @Transactional
    public void cadastrarNovoUsuario(UsuarioDTO usuarioDTO) {
        User user = prepararUsuario(usuarioDTO);
        existeUsuarioNoBanco(usuarioDTO);
        inserirUsuarioNoGrupo(user);
    }

    public User prepararUsuario(UsuarioDTO usuarioDTO){
        usuarioDTO = definirStatusDoUsuario(usuarioDTO);
        User user = usuarioAssembler.dtoToEntity(usuarioDTO);
        user.setSenha(gerarSenhaParaUsuario());
        enviarEmailParaUsuarioComSenha(user);
        return user;
    }

    @Transactional
    public User inserirUsuarioNoGrupo(User user) {

        if(groupHaveSpaceService.groupHaveSpace(user.getIdGrupo())){
            try {
                usuarioRepository.save(user);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Page<UsuarioDTO> recuperarTodos(Pageable pageable) {
        Page<User> usuarios = buscarUsuariosNoBanco(pageable);
        List<UsuarioDTO> usuarioDTOList = usuarioAssembler.entityToManyDtos(usuarios.getContent());
        Page<UsuarioDTO> usuarioDTOS = new PageImpl<>(usuarioDTOList);
        return usuarioDTOS;
    }

    @Override
    public UsuarioDTO recuperarPeloId(String id) {
        Optional<User> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            excecaoService.lancaExcecao(HttpStatus.NOT_FOUND, "Usuario inexistente");
        }
        return usuarioAssembler.entityToDto(usuario.get());
    }

    @Override
    public List<User> recuperarTodosUsuariosPeloIdDoGrupo(String id_grupo) {
        return usuarioRepository.findAllById(id_grupo);
    }

    @Override
    @Transactional
    public void atualizarUsuario(UsuarioDTO usuarioDTO) {
        User user = usuarioAssembler.dtoToEntity(usuarioDTO);
        try {
            usuarioRepository.save(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean usuarioEAdministrador(UsuarioDTO usuarioDTO) {
        Optional<User> usuario = usuarioRepository.buscarAdmPeloId(usuarioDTO.getId_usuario());
        return usuario.isPresent();
    }

    @Override
    public UsuarioDTO buscarAdmDoSistema() {
        if(!verificarSeExisteAdministrador()){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT, "Usuario Administrador não existe");
        }
        Optional<User> userAdmin = usuarioRepository.findByAdmin("S");
        UsuarioDTO usuarioDTO = usuarioAssembler.entityToDto(userAdmin.get());
        return usuarioDTO;
    }

    @Override
    @Transactional
    public void deletarTodosOsUsuarios() {
        usuarioRepository.deleteAll();
    }

    private Page<User> buscarUsuariosNoBanco(Pageable pageable){
        Page<User> usuarios = null;
        try {
            usuarios = usuarioRepository.findAll(pageable);
        } catch (Exception e){
            e.printStackTrace();
        }
        return usuarios;
    }

    private void enviarEmailParaUsuarioComSenha(User user){
        EmailDTO emailDTO = EmailDTO.builder()
                .emailDestinatario(user.getEmail())
                .nomeDestino(user.getNome())
                .senha(user.getSenha())
                .build();
        emailService.enviarEmailComSenha(emailDTO);
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
        Optional<User> usuarioAdm = usuarioRepository.findByAdmin("S");
        return usuarioAdm.isPresent();
    }

    private void existeUsuarioNoBanco(UsuarioDTO usuarioDto){
        Optional<User> usuario = usuarioRepository.findByEmail(usuarioDto.getEmail());
        if(usuario.isPresent()){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT,"Usuario já cadastrado");
        }
    }

}
