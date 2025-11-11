package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.Objetivo;
import com.example.nutriplan_backend.service.ObjetivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivo")
public class ObjetivoController {
    @Autowired
    private ObjetivoService objetivoService;

    // Obtener todo
    @GetMapping
    public ResponseEntity<List<Objetivo>> obtenerTodos(){
        List<Objetivo> lista = objetivoService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Objetivo> obtenerPorId(@PathVariable Long id){
        Objetivo consulta = objetivoService.obtenerPorId(id);
        return ResponseEntity.ok(consulta);
    }
}
