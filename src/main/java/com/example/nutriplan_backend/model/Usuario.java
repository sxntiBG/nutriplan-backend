package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;

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

    // Relación con DatosNutricionales
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-datos")
    private List<DatosNutricionales> datosNutricionales = new ArrayList<>();

    // Relación con PlanUsuario
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("usuario-planes")
    private List<PlanUsuario> planesUsuario = new ArrayList<>();
}
