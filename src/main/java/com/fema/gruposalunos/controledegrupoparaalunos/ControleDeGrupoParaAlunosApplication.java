package com.fema.gruposalunos.controledegrupoparaalunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
public class ControleDeGrupoParaAlunosApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ControleDeGrupoParaAlunosApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ControleDeGrupoParaAlunosApplication.class);
	}
}
