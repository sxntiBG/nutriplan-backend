package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.example.nutriplan_backend.model.enums.Genero;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity // Marca la clase como una entidad JPA (mapeada a una tabla)
@Data // Genera getters, setters, toString, equals y hashCode automáticamente con Lombok
@NoArgsConstructor // Genera un constructor vacío
@Table(name = "datos_nutricionales_usuario") // Mapea a la tabla específica
public class DatosNutricionales {
    @Id //Llave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincremental
    @Column(name="id_dato") //Nombre exacto de la columna en la BD
    private Integer idDato;

    @ManyToOne(fetch = FetchType.EAGER) // Muchos registros nutricionales pertenecen a un usuario
    @JoinColumn(name= "id_usuario", nullable = false)
    @JsonBackReference(value="usuario-datos")
    private Usuario usuario;

    @Column(name="peso_kg")
    private double pesoKg;

    @Column(name="estatura_m")
    private double estaturaCm;

    private int edad;

    @Enumerated(EnumType.STRING)// Guarda el enum como texto ('M', 'F', 'Otro')
    private Genero genero;

    private double tmb;

    @ManyToOne // Muchos datos nutricionales pueden pertenecer a 1 actividad física
    @JoinColumn(name="id_actividad")
    @JsonBackReference(value="actividad-datos")
    private ActividadFisica actividad;

    @Column(name="requerimiento_calorico")
    private double requerimientoCalorico;

    private double imc;
    @Column(name="clasificacion_imc")
    private String clasificacionImc;

    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;

}
