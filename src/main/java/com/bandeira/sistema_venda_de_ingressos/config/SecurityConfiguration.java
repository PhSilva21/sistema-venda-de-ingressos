package com.bandeira.sistema_venda_de_ingressos.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/tickets/buy").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-lower-east").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-upper-east").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-lower-west").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-upper-west").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-lower-south").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-upper-south").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-lower-north").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/tickets/filter-upper-north").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-lower-east").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-upper-east").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-lower-west").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-upper-west").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-lower-south").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-upper-south").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-lower-north").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/insert-upper-north").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/deleteAll").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/create-user").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/plans/create-plan").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/delete-by-id/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/v1/users/update-plan").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
