package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import com.example.nutriplan_backend.model.ActividadFisica;
import com.example.nutriplan_backend.repository.ActividadFisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadFisicaService {
    @Autowired
    private ActividadFisicaRepository actividadFisicaRepository;

    // Obtener todas
    public List<ActividadFisica> obtenerTodas(){
        return actividadFisicaRepository.findAll();
    }

    // Obtener por ID
    public ActividadFisica obtenerPorId(Integer id){
        return actividadFisicaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }
}
