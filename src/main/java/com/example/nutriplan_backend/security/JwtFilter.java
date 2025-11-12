package com.example.nutriplan_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

    String path = request.getRequestURI();
    String method = request.getMethod();

    // Permitir solo las rutas públicas: login y creación de usuario
    if (path.startsWith("/api/auth/") || (path.startsWith("/api/usuarios") && method.equals("POST"))) {
        filterChain.doFilter(request, response);
        return;
    }

    String header = request.getHeader("Authorization");
    String token = null;
    String correo = null;

    if (header != null && header.startsWith("Bearer ")) {
        token = header.substring(7);
        correo = jwtUtil.obtenerCorreoDesdeToken(token);
    }
    

    if (correo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        if (jwtUtil.validarToken(token)) {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(correo, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
    }

    filterChain.doFilter(request, response);
}

}
