package com.example.nutriplan_backend.service;
import com.example.nutriplan_backend.model.DatosNutricionales;
import com.example.nutriplan_backend.repository.DatosNutricionalesRepository;
import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DatosNutricionalesService {
    @Autowired
    private DatosNutricionalesRepository datosNutricionalesRepository;

    // POST
    public DatosNutricionales crearDatosNutricionales(DatosNutricionales datosNutricionales){
        return datosNutricionalesRepository.save(datosNutricionales);
    }

    // GET ALL
    public List<DatosNutricionales> obtenerTodosLosRegistros(){
        return datosNutricionalesRepository.findAll();
    }

    // GET BY ID
    public Optional<DatosNutricionales> obtenerRegistrosPorUsuario(Long id){
        return datosNutricionalesRepository.findById(id);
    }

    // PUT
    public DatosNutricionales actualizarDatos(Long id, DatosNutricionales datoNutricionalNuevo){
           return datosNutricionalesRepository.findById(id).map(datoExistente -> {

        datoExistente.setPesoKg(datoNutricionalNuevo.getPesoKg());
        datoExistente.setEstaturaM(datoNutricionalNuevo.getEstaturaM());
        datoExistente.setEdad(datoNutricionalNuevo.getEdad());
        datoExistente.setGenero(datoNutricionalNuevo.getGenero());
        datoExistente.setTmb(datoNutricionalNuevo.getTmb());
        datoExistente.setActividad(datoNutricionalNuevo.getActividad());
        datoExistente.setRequerimientoCalorico(datoNutricionalNuevo.getRequerimientoCalorico());
        datoExistente.setImc(datoNutricionalNuevo.getImc());

        return datosNutricionalesRepository.save(datoExistente);

    }).orElseThrow(() -> new ResourceNotFoundException(
            "Error: Registro de datos nutricionales con id " + id + " no encontrado."));
}

    // DELETE
    public void eliminarRegistro(Long id){
        if(!datosNutricionalesRepository.existsById(id)){
            throw new ResourceNotFoundException("Error: Registro no encontrado");
        }

        datosNutricionalesRepository.deleteById(id);
    }
}
