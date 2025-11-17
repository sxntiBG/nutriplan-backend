package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.GrupoAlimento;
import com.example.nutriplan_backend.service.GrupoAlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupos-alimentos")
public class GruposAlimentosController {

    @Autowired
    private GrupoAlimentoService gruposAlimentosService;

    // Obtener todos
    @GetMapping
    public ResponseEntity<List<GrupoAlimento>> obtenerTodos() {
        List<GrupoAlimento> lista = gruposAlimentosService.obtenerTodos();
        return ResponseEntity.ok(lista);
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public ResponseEntity<GrupoAlimento> obtenerPorId(@PathVariable Integer id) {
        GrupoAlimento consulta = gruposAlimentosService.obtenerPorId(id);
        return ResponseEntity.ok(consulta);
    }
}
