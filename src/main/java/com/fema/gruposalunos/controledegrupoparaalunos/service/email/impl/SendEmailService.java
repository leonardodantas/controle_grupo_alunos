package com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IMessageEmail;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.ISendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SendEmailService implements ISendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(IMessageEmail messageEmail) {
        MimeMessage mail = mailSender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo(messageEmail.getAddressee());
            helper.setSubject(messageEmail.getSubject());
            helper.setText(messageEmail.getBody(), messageEmail.isHtml());
            mailSender.send(mail);

        } catch (MessagingException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }
    }
}
