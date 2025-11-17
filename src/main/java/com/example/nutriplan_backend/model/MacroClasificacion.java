package com.example.nutriplan_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;

@Entity
@Table(name = "macro_clasificacion")
@Data
@NoArgsConstructor
public class MacroClasificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_macro")
    private Integer id;

    @Column(name = "nombre_macro")
    private String nombre;

    private String descripcion;

    // Relación One-To-Many con GrupoAlimento
    @OneToMany(mappedBy = "macroClasificacion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<GrupoAlimento> gruposAlimento;
}
