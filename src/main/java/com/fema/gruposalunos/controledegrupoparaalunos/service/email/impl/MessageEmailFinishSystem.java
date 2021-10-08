package com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.grupo.dto.GrupoDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.dto.UsuarioDTO;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IMessageEmail;

import java.util.List;

public class MessageEmailFinishSystem implements IMessageEmail {

    private final String addressee;
    private final String subject;
    private final String body;

    private MessageEmailFinishSystem(Builder builder) {
        this.addressee = builder.addressee;
        this.subject = builder.subject;
        this.body = builder.body;
    }

    @Override
    public String getAddressee() {
        return addressee;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public boolean isHtml() {
        return true;
    }


    public static class Builder {

        private static final String GRUPOS_MONTADOS = "GRUPOS MONTADOS";
        private final String addressee;
        private final String subject;
        private String body;

        public Builder(UsuarioDTO usuarioDTO) {
            this.addressee = usuarioDTO.getEmail();
            this.subject = GRUPOS_MONTADOS;
        }

        public Builder body(List<GrupoDTO> grupoDTOS) {
            this.body = createBody(grupoDTOS);
            return this;
        }

        private String createBody(List<GrupoDTO> grupoDTOS) {
            StringBuilder conteudoEmail = new StringBuilder();
            grupoDTOS.forEach(grupo -> createHtmlGroups(conteudoEmail, grupo));
            return conteudoEmail.toString();
        }

        private void createHtmlGroups(StringBuilder conteudoEmail, GrupoDTO grupo) {
            conteudoEmail.append("<h3>").append(grupo.getDescricao()).append("</h3>");
            grupo.getUsuariosDTOList()
                    .forEach(integrante -> {
                        conteudoEmail.append("<p>").append(integrante.getNome()).append("</p>");
                    });
            conteudoEmail.append("<hr>");
        }

        public MessageEmailFinishSystem build(){
            return new MessageEmailFinishSystem(this);
        }

    }
}
