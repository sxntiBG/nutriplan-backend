package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.model.Usuario;
import com.example.nutriplan_backend.repository.UsuarioRepository;
import com.example.nutriplan_backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear usuario (POST)
    public Usuario crearUsuario(Usuario usuario) {
        // Cifrar la contraseña antes de guardar
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setActivo(true); // Se asegura que al crear esté activo
        return usuarioRepository.save(usuario);
    }

    // Obtener todos los usuarios activos
    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .filter(Usuario::isActivo)
                .toList();
    }
    // Obtener usuario por ID
    public Usuario obtenerUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado."));
    }

    // Actualizar usuario (PUT)
    public Usuario actualizarUsuario(Integer id, Usuario detalles) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado."));

        usuarioExistente.setNombre(detalles.getNombre());
        usuarioExistente.setCorreo(detalles.getCorreo());

        if (detalles.getContrasena() != null && !detalles.getContrasena().isBlank()) {
            usuarioExistente.setContrasena(passwordEncoder.encode(detalles.getContrasena()));
        }

        return usuarioRepository.save(usuarioExistente);
    }

    // Desactivar usuario (DELETE lógico)
    public void desactivarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario con ID " + id + " no encontrado."));

        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    public boolean existeCorreo(String correo) {
        return usuarioRepository.existsByCorreo(correo);
    }
}
