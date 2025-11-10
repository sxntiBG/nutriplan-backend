package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alimentos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private Double gramosPorPorcion;

    private Double kcalPorPorcion;

    private Double protePorPorcion;

    private Double carboPorPorcion;

    private Double grasePorPorcion;
}
