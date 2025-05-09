package com.copel.Jornada.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desativa CSRF (útil para APIs REST)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll() // Libera acesso a rotas /api/nome-da-rota
                .anyRequest().permitAll()
            )
            .formLogin(form -> form.disable()) // Desativa tela de login
            .httpBasic(httpBasic -> httpBasic.disable()); // Desativa HTTP Basic

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}