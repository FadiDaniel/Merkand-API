package com.merkand.api.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.merkand.api.dto.response.ErrorResponse; // Asumiendo que esta clase existe
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Clase que maneja la respuesta cuando un usuario autenticado (con token válido)
 * intenta acceder a un recurso para el cual no tiene el rol necesario (HTTP 403 Forbidden).
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        // Configurar la respuesta
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ErrorResponse errorDetails = new ErrorResponse(
                "Access denied: Not an administrator"
        );

        OutputStream out = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, errorDetails);
        out.flush();
    }
}
