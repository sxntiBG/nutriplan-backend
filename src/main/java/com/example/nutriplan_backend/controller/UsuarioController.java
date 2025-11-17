package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.Usuario;
import com.example.nutriplan_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Crear un nuevo usuario
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        // 🔹 Verificar si el correo ya existe
        boolean existeCorreo = usuarioService.existeCorreo(usuario.getCorreo());
        if (existeCorreo) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "El correo ya está registrado."));
        }

        // 🔹 Crear el nuevo usuario
        Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/existe/{correo}")
    public ResponseEntity<Boolean> existeCorreo(@PathVariable String correo) {
        boolean existe = usuarioService.existeCorreo(correo);
        return ResponseEntity.ok(existe);
    }


    // Obtener todos los usuarios activos
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        Usuario usuario = usuarioService.obtenerUsuario(id);
        return ResponseEntity.ok(usuario);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario detalles) {
        Usuario usuarioActualizado = usuarioService.actualizarUsuario(id, detalles);
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Desactivar usuario (eliminación lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> desactivarUsuario(@PathVariable Integer id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.ok("Usuario con ID " + id + " desactivado correctamente.");
    }
}
