package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "alimentos")
@Data
@NoArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alimento")
    private Integer id;

    @Column(name = "nombre_alimento")
    private String nombre;

    @Column(name = "gramos_por_porcion")
    private Double gramosPorPorcion;

    @Column(name = "porcion_estandar_desc")
    private String porcionEstandar;

    @Column(name = "kcal_por_porcion")
    private Double kcalPorPorcion;

    @Column(name = "prote_por_porcion")
    private Double protePorPorcion;

    @Column(name = "grasa_por_porcion")
    private Double grasaPorPorcion;

    @Column(name = "carbo_por_porcion")
    private Double carboPorPorcion;

    // Relación Many-To-One con GrupoAlimento
    @ManyToOne
    @JoinColumn(name = "id_grupo")
    @JsonBackReference
    private GrupoAlimento grupoAlimento;
}
