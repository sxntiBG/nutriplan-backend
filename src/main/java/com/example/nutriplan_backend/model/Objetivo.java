package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Para las relaciones

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "objetivo")
//@Data
//@NoArgsConstructor
public class Objetivo {
   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_objetivo")
    private Long id;

    @Column(name = "nombre_objetivo")
    private String nombre;

    private String descripcion;

    @Column(name = "carbohidratos_pct")
    private double carbohidratos;

    @Column(name = "proteinas_pct")
    private double proteinas;

    @Column(name = "grasas_ptc")
    private double grasas;

    // Relación One-To-Many con PlanUsuario
    //@OneToMany(mappedBy = "objetivo", cascade = CascadeType.ALL, orphanRemoval = true)
   // @JsonManagedReference
    //private List<PlanUsuario> planUsuario = new ArrayList<>();*/
}
