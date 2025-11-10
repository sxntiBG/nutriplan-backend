package com.example.nutriplan_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "objetivo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo")
    private Long id;

    @Column(name = "nombre_objetivo")
    private String nombreObjetivo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "carbohidratos_pct")
    private Double carbohidratosPct;

    @Column(name = "proteinas_pct")
    private Double proteinasPct;

    @Column(name = "grasas_pct")
    private Double grasasPct;

    // Relación One-To-Many con DatosNutricionalesUsuario
    @OneToMany(mappedBy = "objetivo", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<PlanUsuario> planUsuario = new ArrayList<>();
}