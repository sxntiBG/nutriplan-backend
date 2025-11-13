package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType; // Para las relaciones
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(unique = true, nullable = false, length = 150)
    private String correo;

    @Column(nullable = false, length = 255)
    private String contrasena;

    @Column(nullable = false)
    private boolean activo = true;

    // Relación One-To-Many
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true) // orphamRemoval para eliminar en cascada
    @JsonManagedReference(value="usuario-datos")
    private List<DatosNutricionales> datosNutricionales = new ArrayList<>(); // Al ser relación de uno a muchos por eso es una lista

    // Relación con PlanUsuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-planes")
    private List<PlanUsuario> planesUsuario = new ArrayList<>();
}
