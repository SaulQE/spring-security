package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuration One
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity hhtSecurity) throws Exception {
        return hhtSecurity
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/v1/index2").permitAll(); // antMatchers: para almacenar endpoints sin seguridad
                    auth.anyRequest().authenticated(); // Para cualquier otro endpoint debe estar auth
                })
                .formLogin(formLogin -> {
                    formLogin.successHandler(successHandler());
                    formLogin.permitAll();
                })
                .build();
    }

    public AuthenticationSuccessHandler successHandler(){
        return ((request, response, authentication) -> {
            response.sendRedirect("/v1/index");
        });
    }


}
