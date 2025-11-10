package com.example.nutriplan_backend.repository;

import com.example.nutriplan_backend.model.ActividadFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadFisicaRepository extends JpaRepository<ActividadFisica, Long> {

}