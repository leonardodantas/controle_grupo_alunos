package com.fema.gruposalunos.controledegrupoparaalunos.service.email;

public interface IMessageEmail {

    String getAddressee();
    String getSubject();
    String getBody();
    boolean isHtml();
}
