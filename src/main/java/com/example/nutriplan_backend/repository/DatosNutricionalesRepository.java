package com.example.nutriplan_backend.repository;

import com.example.nutriplan_backend.model.DatosNutricionales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DatosNutricionalesRepository extends JpaRepository <DatosNutricionales, Integer> {
    
}
