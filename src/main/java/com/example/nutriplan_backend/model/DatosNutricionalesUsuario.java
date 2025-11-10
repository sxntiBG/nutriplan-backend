package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "datos_nutricionales_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DatosNutricionalesUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dato")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "peso_kg")
    private Double pesoKg;

    @Column(name = "estatura_m")
    private Double estaturaM;

    @Column(name = "edad")
    private Integer edad;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero")
    private Genero genero;

    @Column(name = "tmb")
    private Double tmb;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad")
    private ActividadFisica actividadFisica;

    @Column(name = "requerimiento_calorico")
    private Double requerimientoCalorico;

    @Column(name = "imc")
    private Double imc;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    public enum Genero {
        M,
        F
    }
}