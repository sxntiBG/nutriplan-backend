package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.model.PlanUsuario;
import com.example.nutriplan_backend.model.Usuario;
import com.example.nutriplan_backend.repository.PlanUsuarioRepository;
import com.example.nutriplan_backend.repository.UsuarioRepository;
import com.example.nutriplan_backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlanUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlanUsuarioRepository planUsuarioRepository;
    
}
