package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "grupo_alimento")
@Data
@NoArgsConstructor
public class GrupoAlimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private Long id;

    @Column(name = "nombre_grupo")
    private String nombre;

    @Column(name = "kcal_promedio")
    private double kcalPromedio;

    @Column(name = "prote_promedio_g")
    private double proteinaPromedio;

    @Column(name = "grasa_promedio_g")
    private double grasaPromedio;

    @Column(name = "carbo_promedio_g")
    private double carbohidratoPromedio;

    // Relación Many-To-One con MacroClasificacion
    @ManyToOne
    @JoinColumn(name = "id_macro")
    @JsonBackReference
    private MacroClasificacion macroClasificacion;

    // Relación One-To-Many con Alimentos
    @OneToMany(mappedBy = "grupoAlimento", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Alimento> alimentos;
}
