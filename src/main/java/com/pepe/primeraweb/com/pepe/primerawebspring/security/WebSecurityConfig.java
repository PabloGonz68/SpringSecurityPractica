package com.pepe.primeraweb.com.pepe.primerawebspring.security;

import com.pepe.primeraweb.com.pepe.primerawebspring.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration//Registra Beans
@EnableWebSecurity//para la BBDD
public class WebSecurityConfig {
     /*codificador
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
/*
    @Bean //Usuarios creados de forma LOCAL
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails user1 = User.builder().username("user1")
                .password("$2a$10$qUpbLfpAqiq/..GqLV0GxuxZ7hq7WKFbeMjGgXzcOuofxD4CKDVNW").roles("USER").build();

        UserDetails user2 = User.builder().username("admin")
                .password("$2a$10$qUpbLfpAqiq/..GqLV0GxuxZ7hq7WKFbeMjGgXzcOuofxD4CKDVNW").roles("ADMIN").build();
return new InMemoryUserDetailsManager(user1, user2);
    }*/

/*
    @Autowired
    private DataSource dataSource;//Esta es la fuente de datos, indica que sea seguro trabajar con la BBDD

    @Autowired
    private void configAuthentication(AuthenticationManagerBuilder builder) throws Exception {
    builder.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder()).dataSource(dataSource).
            usersByUsernameQuery("select username, password, enabled from users where username=?")
            .authoritiesByUsernameQuery("select username, role from users where username=?");
    }*/
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }
    //codificador
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//Implementacion del provider, se usa para autenticar usuarios en base de datos
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService());//Los datos
    authenticationProvider.setPasswordEncoder(passwordEncoder());//la contraseña
    return authenticationProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
    //cuando hay una solicitud el adaministrador usara el provider
    authenticationManagerBuilder.authenticationProvider(authenticationProvider());
    return authenticationManagerBuilder.build();
    }

    //Autorización de roles, define las reglas para las entradas HTTP
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(
                auth -> auth.requestMatchers("/personas").permitAll()
                        .requestMatchers("/personas/nueva").hasAnyRole("ADMIN")
                        .requestMatchers("/personas/editar/*", "/personas/eliminar/*").hasAnyRole("ADMIN")//el * es una variable
                        .anyRequest().authenticated()
        ).formLogin(form -> form.loginPage("/login").permitAll()).logout(l -> l.permitAll()).exceptionHandling(e -> e.accessDeniedPage("/403"));
    return  httpSecurity.build();
    }

}
