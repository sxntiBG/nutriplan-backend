package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import com.example.nutriplan_backend.model.GrupoAlimento;
import com.example.nutriplan_backend.repository.GrupoAlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoAlimentoService {

    @Autowired
    private GrupoAlimentoRepository grupoAlimentoRepository;

    // Obtener todos
    public List<GrupoAlimento> obtenerTodos() {
        return grupoAlimentoRepository.findAll();
    }

    // Obtener por ID
    public GrupoAlimento obtenerPorId(Integer id) {
        return grupoAlimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }
}
