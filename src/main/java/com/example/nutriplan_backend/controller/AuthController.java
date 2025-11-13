package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.Usuario;
import com.example.nutriplan_backend.repository.UsuarioRepository;
import com.example.nutriplan_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String correo = credenciales.get("correo");
        String contrasena = credenciales.get("contrasena");

        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);

        if (usuario == null || !usuario.isActivo()) {
            return ResponseEntity.status(401).body(Map.of("error", "Usuario no encontrado o inactivo"));
        }

        if (!passwordEncoder.matches(contrasena, usuario.getContrasena())) {
            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
        }

        String token = jwtUtil.generarToken(correo);
        return ResponseEntity.ok(Map.of(
                "token", token,
                "correo", correo,
                "mensaje", "Inicio de sesión exitoso"
        ));
    }
}
