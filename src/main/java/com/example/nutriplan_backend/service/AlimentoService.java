package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import com.example.nutriplan_backend.model.Alimento;
import com.example.nutriplan_backend.repository.AlimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    // Obtener todos
    public List<Alimento> obtenerTodos() {
        return alimentoRepository.findAll();
    }

    // Obtener por ID
    public Alimento obtenerPorId(Integer id) {
        return alimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }
}
