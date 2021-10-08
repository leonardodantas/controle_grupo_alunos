package com.fema.gruposalunos.controledegrupoparaalunos.service.grupo;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.Grupo;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupoUsuario.GrupoUsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.assembler.GrupoAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.assembler.UsuarioAssembler;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.repository.grupo.IGrupoRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IMessageEmail;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.ISendEmailService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl.MessageEmailFinishSystem;
import com.fema.gruposalunos.controledegrupoparaalunos.service.excecao.IExcecaoService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.usuario.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GrupoService implements IGrupoService {

    @Autowired
    private IGrupoRepository grupoRepository;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private GrupoAssembler grupoAssembler;

    @Autowired
    private UsuarioAssembler usuarioAssembler;

    @Autowired
    private IExcecaoService excecaoService;

    @Autowired
    private IEmailService emailService;

    @Autowired
    private ISendEmailService sendEmailService;

    @Override
    public GrupoDTO cadastrarNovoGrupo(GrupoDTO grupoDTO) {
        Grupo grupo = grupoAssembler.dtoToEntity(grupoDTO);
        Grupo grupoSalvo = salvarGrupoNoBanco(grupo);
        return grupoAssembler.entityToDto(grupoSalvo);
    }

    @Transactional
    private Grupo salvarGrupoNoBanco(Grupo grupo){
        Grupo grupoSalvo = new Grupo();
        try {
            grupoSalvo = grupoRepository.save(grupo);
        } catch (Exception e){
            e.printStackTrace();
        }
        return grupoSalvo;
    }

    @Override
    public Page<GrupoDTO> recuperarTodosOsGrupos(Pageable pageable) {
        Page<Grupo> gruposPage = this.grupoRepository.findAll(pageable);
        return verificarPageGrupoETransformarEmPageDTO(gruposPage);
    }

    public Page<GrupoDTO> verificarPageGrupoETransformarEmPageDTO(Page<Grupo> grupos){
        Page<GrupoDTO> grupoDTOPage = Page.empty();
        if(grupos.getSize() > 0){
            List<GrupoDTO> grupoDTO = this.grupoAssembler.entitysToManyDtos(grupos.getContent());
            grupoDTOPage = new PageImpl<>(grupoDTO);
        }
        return grupoDTOPage;
    }

    private Optional<Grupo> verificarSeExisteGrupoComCodInformado(Long codigo){
        Optional<Grupo> grupo = grupoRepository.findById(codigo);
        return grupo;
    }

    @Override
    public boolean verificarSeGrupoAceitaIntegrate(Long codigo) {

        Optional<Grupo> grupo = verificarSeExisteGrupoComCodInformado(codigo);
        if(!grupo.isPresent()) {
            return false;
        }
        List<Usuario> usuarios = usuarioService.recuperarTodosUsuariosPeloIdDoGrupo(grupo.get().getId());
        return usuarios.size() + 1 < grupo.get().getQuantidadeParticipantes();
    }

    @Override
    public GrupoDTO recuperarPeloId(Long id) {
        Optional<Grupo> grupo = grupoRepository.findById(id);
        if(!grupo.isPresent()){
            excecaoService.lancaExcecao(HttpStatus.NOT_FOUND, "Grupo inexistente");
        }
        return grupoAssembler.entityToDto(grupo.get());
    }

    @Override
    public void inserirUsuarioNoGrupo(GrupoUsuarioDTO grupoUsuarioDTO) {
        GrupoDTO grupoDTO = recuperarPeloId(grupoUsuarioDTO.getId_grupo());
        validaRegrasParaInserirUsuarioNoGrupo(grupoDTO, grupoUsuarioDTO);
    }

    private boolean grupoEstaAberto(GrupoDTO grupoDTO) {
        return grupoDTO.isOpenGrupo();
    }

    @Override
    public Page<GrupoDTO> recuperarTodosOsGruposComParticipantes(Pageable pageable) {

        Page<Grupo> grupos = grupoRepository.findAll(pageable);
        List<GrupoDTO> grupoDTOS = organizaUsuariosNosGrupos(grupos);

        return new PageImpl<>(grupoDTOS);
    }

    @Override
    public void finalizarGrupo(Long id) {
        Optional<Grupo> grupo = verificarSeExisteGrupoComCodInformado(id);
        if(grupo.isPresent()){
            grupo.get().setOpenGrupo(false);
            salvarGrupoNoBanco(grupo.get());
        }
    }

    @Transactional
    public void deletarTodosOsGrupos() {
        grupoRepository.deleteAll();
    }

    @Override
    public List<GrupoDTO> recuperarTodosOsGrupoComParticipantesSemPaginacao() {
        Page<GrupoDTO> grupoDTOPage = recuperarTodosOsGrupos(Pageable.unpaged());
        List<GrupoDTO> grupoDTOS = new ArrayList<GrupoDTO>();
        grupoDTOPage.stream()
                .forEach(grupo -> {
                    List<Usuario> usuarios = usuarioService.recuperarTodosUsuariosPeloIdDoGrupo(grupo.getId_grupo());
                    List<UsuarioDTO> usuarioDTOS = usuarioAssembler.entityToManyDtos(usuarios);
                    grupo.setUsuariosDTOList(usuarioDTOS);
                    grupoDTOS.add(grupo);
                });

        return grupoDTOS;
    }

    @Transactional
    public void finalizarTodosOsGrupos() {
        try {
            grupoRepository.finalizarTodosOsGrupos();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void finalizarSistema() {

        UsuarioDTO usuarioDTO = usuarioService.buscarAdmDoSistema();
        List<GrupoDTO> grupoDTOS = recuperarTodosOsGrupoComParticipantesSemPaginacao();
        finalizarTodosOsGrupos();
        usuarioService.deletarTodosOsUsuarios();
        deletarTodosOsGrupos();

        IMessageEmail message = new MessageEmailFinishSystem.Builder(usuarioDTO).body(grupoDTOS).build();
        sendEmailService.sendEmail(message);
    }

    private List<GrupoDTO> organizaUsuariosNosGrupos(Page<Grupo> grupos) {
        List<GrupoDTO> grupoDTOS = new ArrayList<GrupoDTO>();
        grupos.forEach(grupo -> {
            List<Usuario> usuarios = usuarioService.recuperarTodosUsuariosPeloIdDoGrupo(grupo.getId());
            GrupoDTO grupoDTO = GrupoDTO.builder()
                    .id_grupo(grupo.getId())
                    .descricao(grupo.getDescricao())
                    .qtde_participantes(grupo.getQuantidadeParticipantes())
                    .usuariosDTOList(usuarioAssembler.entityToManyDtos(usuarios))
                    .build();
            grupoDTOS.add(grupoDTO);

        });
        return grupoDTOS;
    }

    private void validaRegrasParaInserirUsuarioNoGrupo(GrupoDTO grupoDTO, GrupoUsuarioDTO grupoUsuarioDTO) {
        int quantidadeAtual = quantidadeAtualDeIntegrantesDoGrupo(grupoUsuarioDTO.getId_grupo());
        if(!grupoEstaAberto(grupoDTO) || (quantidadeAtual + 1 > grupoDTO.getQtde_participantes())) {
           excecaoService.lancaExcecao(HttpStatus.CONFLICT, "Grupo está fechado ou Quantidade será excedida");
        }
        recuperaUsuarioPeloGrupoUsuarioId(grupoUsuarioDTO);
    }

    private void recuperaUsuarioPeloGrupoUsuarioId(GrupoUsuarioDTO grupoUsuarioDTO){
        UsuarioDTO usuarioDTO = usuarioService.recuperarPeloId(grupoUsuarioDTO.getId_usuario());
        if(usuarioService.usuarioEAdministrador(usuarioDTO)){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT, "Usuario é administrador");
        }
        adicionaUsuarioAoGrupoNoBanco(usuarioDTO, grupoUsuarioDTO.getId_grupo());
    }

    @Transactional
    private void adicionaUsuarioAoGrupoNoBanco(UsuarioDTO usuarioDTO, Long idGrupo){
        try {
            usuarioDTO.setGrupo(GrupoDTO.builder().id_grupo(idGrupo).build());
            usuarioService.atualizarUsuario(usuarioDTO);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int quantidadeAtualDeIntegrantesDoGrupo(Long id_grupo) {
        return grupoRepository.quantidadeAtualDeIntegrantesDoGrupo(id_grupo);
    }

}
