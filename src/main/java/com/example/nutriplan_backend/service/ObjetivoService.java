package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import com.example.nutriplan_backend.model.Objetivo;
import com.example.nutriplan_backend.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjetivoService {
    @Autowired
    private ObjetivoRepository objetivoRepository;

    // Obtener todas
    public List<Objetivo> obtenerTodos(){
        return objetivoRepository.findAll();
    }

    // Obtener por ID
    public Objetivo obtenerPorId(Integer id){
        return objetivoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }
}
