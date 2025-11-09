package com.example.nutriplan_backend.repository;

import com.example.nutriplan_backend.model.PlanUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// Interface para acceso a los métodos de JPA porque vamos a crear el CRUD
public interface PlanUsuarioRepository extends JpaRepository<PlanUsuario, Long> {
    
}