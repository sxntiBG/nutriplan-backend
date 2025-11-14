package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "objetivo")
@Data
@NoArgsConstructor
public class Objetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_objetivo")
    private Integer id;

    @Column(name = "nombre_objetivo")
    private String nombre;

    private String descripcion;

    @Column(name = "carbohidratos_pct")
    private double carbohidratos;

    @Column(name = "proteinas_pct")
    private double proteinas;

    @Column(name = "grasas_pct")
    private double grasas;

    @OneToMany(mappedBy = "objetivo")
    @JsonBackReference("objetivo-planes")
    private List<PlanUsuario> planesUsuario = new ArrayList<>();
}
