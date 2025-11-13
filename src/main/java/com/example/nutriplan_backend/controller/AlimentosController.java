package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.Alimento;
import com.example.nutriplan_backend.service.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentosController {

    @Autowired
    private AlimentoService alimentosService;

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<Alimento>> obtenerTodos() {
        List<Alimento> lista = alimentosService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alimento> obtenerPorId(@PathVariable Long id) {
        Alimento consulta = alimentosService.obtenerPorId(id);
        return ResponseEntity.ok(consulta);
    }
}
