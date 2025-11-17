package com.example.nutriplan_backend.repository;

import com.example.nutriplan_backend.model.GrupoAlimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoAlimentoRepository extends JpaRepository<GrupoAlimento, Integer> {
}
