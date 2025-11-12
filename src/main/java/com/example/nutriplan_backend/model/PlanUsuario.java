package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "plan_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan_usuario")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("usuario-planes")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("objetivo-planes")
    @JoinColumn(name = "id_objetivo")
    private Objetivo objetivo;

    @Column(name = "calorias_totales")
    private Double caloriasTotales;

    @Column(name = "carbohidratos_g")
    private Double carbohidratosG;

    @Column(name = "proteinas_g")
    private Double proteinasG;

    @Column(name = "grasas_g")
    private Double grasasG;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}