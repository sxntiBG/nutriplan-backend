package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import com.example.nutriplan_backend.model.MacroClasificacion;
import com.example.nutriplan_backend.repository.MacroClasificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MacroClasificacionService {

    @Autowired
    private MacroClasificacionRepository macroClasificacionRepository;

    // Obtener todos
    public List<MacroClasificacion> obtenerTodos() {
        return macroClasificacionRepository.findAll();
    }

    // Obtener por ID
    public MacroClasificacion obtenerPorId(Integer id) {
        return macroClasificacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }
}
