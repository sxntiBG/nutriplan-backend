package com.example.nutriplan_backend.service;
import com.example.nutriplan_backend.model.DatosNutricionales;
import com.example.nutriplan_backend.repository.DatosNutricionalesRepository;
import com.example.nutriplan_backend.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.nutriplan_backend.repository.ActividadFisicaRepository;
import com.example.nutriplan_backend.model.ActividadFisica ;

@Service
public class DatosNutricionalesService {
    @Autowired
    private DatosNutricionalesRepository datosNutricionalesRepository;
    @Autowired
    private ActividadFisicaRepository actividadFisicaRepository;

    // POST
    public DatosNutricionales crearDatosNutricionales(DatosNutricionales datosNutricionales){

        //Se invoca el metodo para calcular la tmb
        double tmbCalculada = calcularTMB(datosNutricionales);
        //Reasigna el valor de tmb en la tabla, despues de realizar los calculos.
        datosNutricionales.setTmb(tmbCalculada);

        //Obtiene el id de la actividad fisica.
        long idActividad = datosNutricionales.getActividad().getId();
        //Invoca el metodo para calcular el requerimiento calorico.
        //Pasa como parametros la tmb y el id de la actividad fisica.
        double rqtoKcalCalculado = calcularRequerimientoKcal(tmbCalculada, idActividad);
        //Reasigna el valor de la columna requerimiento_calorico en la tabla, despues de realizar los calculos.
        datosNutricionales.setRequerimientoCalorico(rqtoKcalCalculado);

        //Invoca el metodo para calcular el IMC
        double imcCalculado = calcularIMC(datosNutricionales);
        //Reasigna el valor de la columna imc en la tabla, despues de realizar los calculos.
        datosNutricionales.setImc(imcCalculado);

        return datosNutricionalesRepository.save(datosNutricionales);
    }

    // GET ALL
    public List<DatosNutricionales> obtenerTodosLosRegistros(){
        return datosNutricionalesRepository.findAll();
    }

    // GET BY ID
    public DatosNutricionales obtenerRegistrosPorUsuario(Long id){
        return datosNutricionalesRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
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



    //Metodo para calcular la TMB 
    public double calcularTMB(DatosNutricionales dn){
        //Depende del genero se utiliza una formula diferente.
        switch (dn.getGenero()) {
        case M:
        //Formula para el genero masculino
            return (10 * dn.getPesoKg()) + (6.25 * dn.getEstaturaM()) - (5 * dn.getEdad()) + 5;

        case F:
        //Formula para el genero femenino
            return (10 * dn.getPesoKg()) + (6.25 * dn.getEstaturaM()) - (5 * dn.getEdad()) - 161;

        default:
           return 0;
    }
}

//Metodo para calcular el requerimiento calorico.
//Busca el id de actividad fisica seleccionado
//Si no lo encuentra devuelve error
//Si existe, obtiene el factor asociado al id y realiza el calculo
public double calcularRequerimientoKcal(double tmb, Long idActividad){

     ActividadFisica actividad = actividadFisicaRepository.findById(idActividad)
        .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));


    return tmb * actividad.getFactor();
}

//Metodo para calcular el IMC
public double calcularIMC(DatosNutricionales dn){
    return dn.getPesoKg() / (dn.getEstaturaM() * dn.getEstaturaM());
}

}
