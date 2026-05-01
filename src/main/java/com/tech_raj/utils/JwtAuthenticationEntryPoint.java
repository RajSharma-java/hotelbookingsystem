package com.tech_raj.utils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException)
            throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");

        String message;

        if (request.getAttribute("expired") != null) {
            message = "Token Expired";
        } else if (request.getAttribute("invalid") != null) {
            message = "Invalid Token";
        } else {
            message = "Missing Token";
        }

        response.getWriter().write(
                "{\"message\": \"" + message + "\", \"status\": 401}"
        );
    }
}
