package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference; // Para las relaciones
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String correo;
    private String contrasena;

    // Relación One-To-Many
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // orphamRemoval para eliminar en cascada

    @JsonManagedReference
    private List<DatosNutricionales> datosNutricionales = new ArrayList<>(); // Al ser relación de uno a muchos por eso es una lista
    private List<PlanUsuario> planUsuario = new ArrayList<>();
}
