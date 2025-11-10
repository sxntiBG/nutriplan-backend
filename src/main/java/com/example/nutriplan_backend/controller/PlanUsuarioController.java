package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.PlanUsuario;
import com.example.nutriplan_backend.service.PlanUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plan")
@CrossOrigin(origins = "*")
public class PlanUsuarioController {

    @Autowired
    private PlanUsuarioService planUsuarioService;

    /**
     * Genera y guarda un plan nutricional nuevo para el usuario.
     * Ejemplo de uso desde frontend:
     * POST /api/plan/generar?usuarioId=1&objetivoId=2
     */
    @PostMapping("/generar")
    public ResponseEntity<PlanUsuario> generarPlan(
            @RequestParam Long usuarioId,
            @RequestParam Long objetivoId) {

        PlanUsuario plan = planUsuarioService.generarYGuardarPlan(usuarioId, objetivoId);
        return ResponseEntity.ok(plan);
    }

    /**
     * Obtiene todos los planes de un usuario
     * GET /api/plan/usuario/1
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PlanUsuario>> obtenerPlanesPorUsuario(@PathVariable Long usuarioId) {
        List<PlanUsuario> planes = planUsuarioService.obtenerPlanesPorUsuario(usuarioId);
        return ResponseEntity.ok(planes);
    }

    /**
     * Elimina un plan por ID
     */
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long planId) {
        planUsuarioService.eliminarPlan(planId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Calcula un plan previo (solo devuelve resultados, no guarda)
     * GET /api/plan/calcular?usuarioId=1&objetivoId=2
     */
    @GetMapping("/calcular")
    public ResponseEntity<Map<String, Object>> calcularPlanPrevio(
            @RequestParam Long usuarioId,
            @RequestParam Long objetivoId) {
        Map<String, Object> resultado = planUsuarioService.calcularPlanPrevio(usuarioId, objetivoId);
        return ResponseEntity.ok(resultado);
    }
}