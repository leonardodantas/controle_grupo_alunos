package com.fema.gruposalunos.controledegrupoparaalunos.config.security;

import com.fema.gruposalunos.controledegrupoparaalunos.repository.usuario.IUsuarioRepository;
import com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao.AutenticacaoService;
import com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao.AutenticacaoViaTokenFilter;
import com.fema.gruposalunos.controledegrupoparaalunos.service.autenticacao.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private AutenticacaoService autenticacaoService;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configuracoes de autenticacao
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(autenticacaoService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    //Configuracoes de autorizacao
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .antMatchers(HttpMethod.GET,"/usuario").permitAll()
                .antMatchers(HttpMethod.GET,"/usuario/*").permitAll()
                .antMatchers(HttpMethod.POST,"/usuario/cadastrar").permitAll()
                .antMatchers(HttpMethod.POST,"/grupo/inserir/usuario/grupo").hasRole("ALUNO")
                .antMatchers(HttpMethod.POST,"/grupo/cadastrar").hasRole("PROFESSOR")
                .antMatchers(HttpMethod.GET,"/grupo").hasAnyRole("PROFESSOR","ALUNO")
                .antMatchers(HttpMethod.GET,"/grupo/*").hasAnyRole("PROFESSOR","ALUNO")
                .antMatchers(HttpMethod.GET,"/grupo/participantes").hasAnyRole("PROFESSOR","ALUNO")
                .antMatchers(HttpMethod.PUT,"/grupo/fechar/*").hasRole("PROFESSOR")
                .antMatchers(HttpMethod.POST,"/grupo/finalizar/sistema").hasRole("PROFESSOR")
                .anyRequest().denyAll()
<<<<<<< HEAD
                .and().cors()
                .and().csrf().disable()
                .addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
=======
                .and().cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
>>>>>>> 68c565c29e4f568562b29ad2138ace79eaac5721
    }


    //Configuracoes de recursos estaticos(js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
    }
}
