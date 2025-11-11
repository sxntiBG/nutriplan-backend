package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.ActividadFisica;
import com.example.nutriplan_backend.service.ActividadFisicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividad-fisica")
public class ActividadFisicaController {
    @Autowired
    private ActividadFisicaService actividadFisicaService;

    // Obtener todo
    @GetMapping
    public ResponseEntity<List<ActividadFisica>> obtenerTodas() {
        List<ActividadFisica> lista = actividadFisicaService.obtenerTodas();
        return ResponseEntity.ok(lista); // 200 OK
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<ActividadFisica> obtenerPorId(@PathVariable Long id){
        ActividadFisica consulta = actividadFisicaService.obtenerPorId(id);
        return ResponseEntity.ok(consulta);
    }
}
