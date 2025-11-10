package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "actividad_fisica")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActividadFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "factor")
    private Double factor;
}
