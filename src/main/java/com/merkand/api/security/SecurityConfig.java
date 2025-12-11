package com.merkand.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod; // Necesario para especificar POST
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;
    private final CustomAccessDeniedHandler accessDeniedHandler; // 1. Inyección del manejador 403

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          UserDetailsService userDetailsService,
                          CustomAccessDeniedHandler accessDeniedHandler) { // 2. Constructor para inyección
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        authManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());

        return authManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // ÚNICA RUTA PÚBLICA: Permite el acceso al login (POST)
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()

                        // RUTA RESTRINGIDA POR ROL: Solo el ADMIN puede registrar nuevos usuarios
                        .requestMatchers(HttpMethod.POST, "/api/auth/register").hasRole("ADMIN")

//                        // Permite acceso público a otras rutas definidas (/public/**)
//                        .requestMatchers("/api/**").permitAll()

                        //  PREDETERMINADO: Todas las demás solicitudes requieren autenticación (JWT)
                        .anyRequest().authenticated()
                )
                // Configuración de Manejo de Excepciones: Conecta el manejador 403
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(accessDeniedHandler) // Maneja el HTTP 403 Forbidden
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}