package com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.groupuser.response.UserGroupResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.User;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.response.UserResponseDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IEmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Component
public class EmailSendService implements IEmailSendService {

    @Autowired
    private JavaMailSender mailSender;

    private static final String GROUPS = "GRUPOS " ;

    @Override
    public void send(UserResponseDTO user, List<UserGroupResponseDTO> groups) {
        groups.forEach(group -> {
            this.sendEmail(user, group);
        });

    }

    private void sendEmail(UserResponseDTO user, UserGroupResponseDTO group) {
        MimeMessage mail = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(user.getEmail());
            helper.setSubject(GROUPS + group.getIdGroup());
            helper.setText(generateText(group), true);
            mailSender.send(mail);
        } catch (MessagingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    private String generateText(UserGroupResponseDTO group) {
        StringBuilder email = new StringBuilder();
        email.append("<h3>" + group.getDescripton() + "</h3>");
        group.getUsers()
                .forEach(integrante -> {
                    email.append("<p>" + integrante.getNome() + "</p>");
                });
        email.append("<hr>");
        return email.toString();
    }
}
