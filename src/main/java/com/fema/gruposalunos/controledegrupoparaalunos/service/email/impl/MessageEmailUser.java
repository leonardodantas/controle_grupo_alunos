package com.fema.gruposalunos.controledegrupoparaalunos.service.email.impl;

import com.fema.gruposalunos.controledegrupoparaalunos.model.usuario.Usuario;
import com.fema.gruposalunos.controledegrupoparaalunos.service.email.IMessageEmail;

public class MessageEmailUser implements IMessageEmail {

    private final String addressee;
    private final String subject;
    private final String body;

    private MessageEmailUser(Builder builder) {
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

        private final String addressee;
        private final String subject;
        private String body;

        public Builder(Usuario usuario) {
            this.addressee = usuario.getEmail();
            this.subject = usuario.getNome();
        }

        public Builder body(Usuario usuario){
            this.body = createBody(usuario);
            return this;
        }

        private String createBody(Usuario usuario) {
            StringBuilder body = new StringBuilder();
            body.append("<p>Sua senha para acessar o sistema de grupo de alunos é ");
            body.append(usuario.getSenha());
            body.append(" </p>");
            return body.toString();
        }

        public MessageEmailUser build(){
            return new MessageEmailUser(this);
        }

    }
}
