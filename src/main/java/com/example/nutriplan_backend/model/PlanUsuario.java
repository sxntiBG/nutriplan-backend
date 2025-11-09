package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Para las relaciones

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor

public class PlanUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private double calorias;
    private double carbohidratos;
    private double proteinas;
    private double grasas;
    private LocalDateTime fecha_creacion;

    // Relación Many to One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    @JoinColumn(name="id_objetivo")

    @JsonBackReference
    private Usuario usuario;
    
    // @JsonBackReference
    // private Objetivo objetivo;
    
}
