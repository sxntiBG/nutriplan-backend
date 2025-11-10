package com.example.nutriplan_backend.service;

import com.example.nutriplan_backend.model.*;
import com.example.nutriplan_backend.repository.*;

import jakarta.transaction.Transactional;

import com.example.nutriplan_backend.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlanUsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DatosNutricionalesRepository datosNutricionalesRepository;
    @Autowired
    private ActividadFisicaRepository actividadFisicaRepository;
    @Autowired
    private ObjetivoRepository objetivoRepository;
    @Autowired
    private PlanUsuarioRepository planUsuarioRepository;

    // POST
    @Transactional
    public PlanUsuario generarYGuardarPlan(Long usuarioId, Long objetivoId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Error: Usuario con ID: " + usuarioId + "no encontrado"));

        DatosNutricionalesUsuario datos = datosNutricionalesRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Datos nutricionales no encontrados para el usuario: " + usuarioId));

        ActividadFisica actividad = actividadFisicaRepository.findById(datos.getActividadFisica().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actividad física no encontrada"));

        Objetivo objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new ResourceNotFoundException("Objetivo no encontrado"));

        double tdee = datos.getTmb() * actividad.getFactor();

        double caloriasTotales = switch (objetivo.getNombreObjetivo().toLowerCase()) {
            case "ganar músculo" -> tdee * 1.15;
            case "perder grasa" -> tdee * 0.85;
            default -> tdee;
        };

        double carbohidratosG = (caloriasTotales * objetivo.getCarbohidratosPct() / 100) / 4;
        double proteinasG = (caloriasTotales * objetivo.getProteinasPct() / 100) / 4;
        double grasasG = (caloriasTotales * objetivo.getGrasasPct() / 100) / 9;

        PlanUsuario plan = new PlanUsuario();
        plan.setUsuario(usuario);
        plan.setObjetivo(objetivo);
        plan.setCaloriasTotales(caloriasTotales);
        plan.setCarbohidratosG(carbohidratosG);
        plan.setProteinasG(proteinasG);
        plan.setGrasasG(grasasG);
        plan.setFechaCreacion(LocalDateTime.now());

        return planUsuarioRepository.save(plan);
    }

    // ✅ NUEVO: calcula sin guardar
    public Map<String, Object> calcularPlanPrevio(Long usuarioId, Long objetivoId) {
        DatosNutricionalesUsuario datos = datosNutricionalesRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Datos nutricionales no encontrados"));

        ActividadFisica actividad = actividadFisicaRepository.findById(datos.getActividadFisica().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Actividad física no encontrada"));

        Objetivo objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new ResourceNotFoundException("Objetivo no encontrado"));

        double tdee = datos.getTmb() * actividad.getFactor();
        double caloriasTotales;
        String nombre = objetivo.getNombreObjetivo().toLowerCase();

        if (nombre.contains("ganar"))
            caloriasTotales = tdee * 1.15;
        else if (nombre.contains("perder"))
            caloriasTotales = tdee * 0.85;
        else
            caloriasTotales = tdee;

        double carbohidratosG = (caloriasTotales * objetivo.getCarbohidratosPct() / 100) / 4;
        double proteinasG = (caloriasTotales * objetivo.getProteinasPct() / 100) / 4;
        double grasasG = (caloriasTotales * objetivo.getGrasasPct() / 100) / 9;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("tdee", tdee);
        resultado.put("caloriasTotales", caloriasTotales);
        resultado.put("carbohidratosG", carbohidratosG);
        resultado.put("proteinasG", proteinasG);
        resultado.put("grasasG", grasasG);

        return resultado;
    }

    public List<PlanUsuario> obtenerPlanesPorUsuario(Long usuarioId) {
        return planUsuarioRepository.findByUsuarioId(usuarioId);
    }

    public void eliminarPlan(Long planId) {
        if (!planUsuarioRepository.existsById(planId)) {
            throw new ResourceNotFoundException("Plan no encontrado con ID: " + planId);
        }
        planUsuarioRepository.deleteById(planId);
    }
}