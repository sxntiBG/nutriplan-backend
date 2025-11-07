package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.model.Usuario;
import com.example.nutriplan_backend.repository.UsuarioRepository;
import com.example.nutriplan_backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired // Inyección de dependencias
    private UsuarioRepository usuarioRepository;

    // POST
    public Usuario postUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // GET ALL
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    // GET BY ID
    public Optional<Usuario> getUsuario(Long id){
        return usuarioRepository.findById(id);
    }

    // PUT
    public Usuario putUsuario(Long id, Usuario detalles){
        return usuarioRepository.findById(id).map(usuarioExistente ->{
            usuarioExistente.setNombre(detalles.getNombre());
            usuarioExistente.setCorreo(detalles.getCorreo());
            usuarioExistente.setContrasena(detalles.getContrasena());

            return usuarioRepository.save(usuarioExistente);
        }).orElseThrow(() -> new ResourceNotFoundException("Error: Usuario con id " + id + " no encontrado."));
    }

    // DELETE
    public void deleteUsuario(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new ResourceNotFoundException("Error: Usuario con id " + id + " no encontrado.");
        }

        usuarioRepository.deleteById(id);
    }
}
