package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Para las relaciones

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actividad_fisica")
@Data
@NoArgsConstructor
public class ActividadFisica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_actividad")
    private Integer id;

    private String nombre;

    private String descripcion;

    private Double factor;

    // Relación One-To-Many con DatosNutricionales
    @OneToMany(mappedBy = "actividad", cascade=CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference(value="actividad-datos")
    private List<DatosNutricionales> datosNutricionales = new ArrayList<>();
}
