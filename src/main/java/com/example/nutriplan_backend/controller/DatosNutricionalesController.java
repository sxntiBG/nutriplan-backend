package com.example.nutriplan_backend.controller;

import com.example.nutriplan_backend.model.DatosNutricionales;
import com.example.nutriplan_backend.service.DatosNutricionalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/datos-nutricionales")
public class DatosNutricionalesController {
    
    @Autowired
    private DatosNutricionalesService datosNutricionalesService;

    //GET: Obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosNutricionales> obtenerPorId(@PathVariable Integer id){
        DatosNutricionales consulta = datosNutricionalesService.obtenerRegistrosPorUsuario(id);
        return ResponseEntity.ok(consulta);
    }

    //POST: Crear un registro nuevo
    @PostMapping
    public ResponseEntity <DatosNutricionales> crearRegistro(@RequestBody DatosNutricionales dn){
        DatosNutricionales nuevoRegistro = datosNutricionalesService.crearDatosNutricionales(dn);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
    }

    //PUT: Actualizar un registro 
    @PutMapping("/{id}")
    public ResponseEntity <DatosNutricionales> actualizarRegistro(
    @PathVariable Integer id,    
    @RequestBody DatosNutricionales dn){
        DatosNutricionales dnActualizado = datosNutricionalesService.actualizarDatos(id,dn);
        return ResponseEntity.ok(dnActualizado);
    }

    //DELETE: Eliminar un registro 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerRegistro(@PathVariable Integer id){
        datosNutricionalesService.eliminarRegistro(id);
        return ResponseEntity.noContent().build(); 
    }
}
