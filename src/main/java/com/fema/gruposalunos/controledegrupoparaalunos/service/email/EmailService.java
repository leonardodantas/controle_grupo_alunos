package com.fema.gruposalunos.controledegrupoparaalunos.service.email;

import com.fema.gruposalunos.controledegrupoparaalunos.model.email.EmailDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.excecao.ExcecaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailService implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private ExcecaoService excecaoService;

    @Override
    public void enviarEmailComSenha(EmailDTO emailDTO) {
        StringBuilder text = new StringBuilder();
        text.append("<p>Sua senha para acessar o sistema de grupo de alunos é ");
        text.append(emailDTO.getSenha());
        text.append(" </p>");
        montarConteudoEnviarEmail(emailDTO.getEmailDestinatario(), "Senha para acesso", text.toString());
    }

    @Override
    public void enviarGruposParaEmailDoAdministrador(List<GrupoDTO> grupoDTOList, UsuarioDTO admin) {
        montarConteudoEnviarEmail(admin.getEmail(), "Grupos Montados", montarConteudoEmail(grupoDTOList));
    }

    private void montarConteudoEnviarEmail(String to, String subject, String text) {
        MimeMessage mail = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            mailSender.send(mail);
        } catch (MessagingException e){
            excecaoService.lancaExcecao(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            excecaoService.lancaExcecao(HttpStatus.CONFLICT, e.getMessage());
        }

    }

    private String montarConteudoEmail(List<GrupoDTO> grupoDTOList){
        StringBuilder conteudoEmail = new StringBuilder();
        grupoDTOList.stream().forEach(grupo -> criarHtmlDosIntegradosDosGrups(conteudoEmail, grupo));

        return conteudoEmail.toString();
    }

    private void criarHtmlDosIntegradosDosGrups(StringBuilder conteudoEmail, GrupoDTO grupo) {
        conteudoEmail.append("<h3>" + grupo.getDescricao() + "</h3>");
        grupo.getUsuariosDTOList()
                .stream()
                .forEach(integrante -> {
                    conteudoEmail.append("<p>" + integrante.getNome() + "</p>");
                });
        conteudoEmail.append("<hr>");
    }

}
