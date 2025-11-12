package com.example.nutriplan_backend.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType; // Para las relaciones
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    //private List<PlanUsuario> planUsuario = new ArrayList<>();
}
