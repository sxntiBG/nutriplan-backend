package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.MacroClasificacion;
import com.example.nutriplan_backend.service.MacroClasificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/macro-clasificacion")
public class MacroClasificacionController {

    @Autowired
    private MacroClasificacionService macroClasificacionService;

    // Obtener todas
    @GetMapping
    public ResponseEntity<List<MacroClasificacion>> obtenerTodas() {
        List<MacroClasificacion> lista = macroClasificacionService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<MacroClasificacion> obtenerPorId(@PathVariable Long id) {
        MacroClasificacion consulta = macroClasificacionService.obtenerPorId(id);
        return ResponseEntity.ok(consulta);
    }
}
