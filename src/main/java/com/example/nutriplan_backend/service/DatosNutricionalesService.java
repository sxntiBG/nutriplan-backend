package com.example.nutriplan_backend.service;
import com.example.nutriplan_backend.model.DatosNutricionales;
import com.example.nutriplan_backend.model.Usuario;
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
        Integer idActividad = datosNutricionales.getActividad().getId();

        //Invoca el metodo para calcular el requerimiento calorico.
        //Pasa como parametros la tmb y el id de la actividad fisica.
        double rqtoKcalCalculado = calcularRequerimientoKcal(tmbCalculada, idActividad);

        //Reasigna el valor de la columna requerimiento_calorico en la tabla, despues de realizar los calculos.
        datosNutricionales.setRequerimientoCalorico(rqtoKcalCalculado);

        //Invoca el metodo para calcular el IMC
        double imcCalculado = calcularIMC(datosNutricionales);
        //Reasigna el valor de la columna imc en la tabla, despues de realizar los calculos.
        datosNutricionales.setImc(imcCalculado);

        //Invoca el metodo para clasificar el resultado del imc
        String imcClasificado = clasificarIMC(imcCalculado);

        //Reasigna el valor del imc clasificado en el campo de la BD 'clasificacion_imc'
        datosNutricionales.setClasificacionImc(imcClasificado);

        return datosNutricionalesRepository.save(datosNutricionales);
    }

    // GET ALL
    public List<DatosNutricionales> obtenerTodosLosRegistros(){
        return datosNutricionalesRepository.findAll();
    }

    // GET BY ID
    public DatosNutricionales obtenerRegistrosPorUsuario(Integer id){
        return datosNutricionalesRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Registro no encontrado"));
    }

    // PUT
    public DatosNutricionales actualizarDatos(Integer id, DatosNutricionales datoNutricionalNuevo){
           return datosNutricionalesRepository.findById(id).map(datoExistente -> {

             // Mantener el usuario actual
            Usuario usuarioActual = datoExistente.getUsuario();
        
            //Cambiar los demas datos
            datoExistente.setPesoKg(datoNutricionalNuevo.getPesoKg());
            datoExistente.setEstaturaCm(datoNutricionalNuevo.getEstaturaCm());
            datoExistente.setEdad(datoNutricionalNuevo.getEdad());
            datoExistente.setGenero(datoNutricionalNuevo.getGenero());

            //Se invoca el metodo para recalcular la tmb
            double tmbCalculada = calcularTMB(datoNutricionalNuevo);
            datoExistente.setTmb(tmbCalculada);

            datoExistente.setActividad(datoNutricionalNuevo.getActividad());

            //Obtiene el id de la actividad fisica.
            Integer idActividad = datoNutricionalNuevo.getActividad().getId();
            //Invoca el metodo para calcular el requerimiento calorico.
            //Pasa como parametros la tmb y el id de la actividad fisica.

            double rqtoKcalCalculado = calcularRequerimientoKcal(tmbCalculada, idActividad);
            //Reasigna el valor de la columna requerimiento_calorico en la tabla, despues de realizar los calculos.
            datoExistente.setRequerimientoCalorico(rqtoKcalCalculado);

            //Invoca el metodo para recalcular el IMC
            double imcCalculado = calcularIMC(datoNutricionalNuevo);
            //Reasigna el valor de la columna imc en la tabla, despues de realizar los calculos.
            datoExistente.setImc(imcCalculado);

            //Invoca el metodo para clasificar el resultado del imc
            String imcClasificado = clasificarIMC(imcCalculado);

            //Reasigna el valor del imc clasificado en el campo de la BD 'clasificacion_imc'
            datoExistente.setClasificacionImc(imcClasificado);

             // Reasignar el usuario para no perderlo
            datoExistente.setUsuario(usuarioActual);

            return datosNutricionalesRepository.save(datoExistente);
    }).orElseThrow(() -> {
        System.out.println(">>> No se encontró registro con id: " + id);
        return new ResourceNotFoundException(
            "Error: Registro de datos nutricionales con id " + id + " no encontrado."
        );
    });
}

    // DELETE
    public void eliminarRegistro(Integer id){
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
            return (10 * dn.getPesoKg()) + (6.25 * dn.getEstaturaCm()) - (5 * dn.getEdad()) + 5;

        case F:
        //Formula para el genero femenino
            return (10 * dn.getPesoKg()) + (6.25 * dn.getEstaturaCm()) - (5 * dn.getEdad()) - 161;

        default:
           return 0;
    }
}

//Metodo para calcular el requerimiento calorico.
//Busca el id de actividad fisica seleccionado
//Si no lo encuentra devuelve error
//Si existe, obtiene el factor asociado al id y realiza el calculo
public double calcularRequerimientoKcal(double tmb, Integer idActividad){

     ActividadFisica actividad = actividadFisicaRepository.findById(idActividad)
        .orElseThrow(() -> new ResourceNotFoundException("Actividad no encontrada"));


    return tmb * actividad.getFactor();
}

//Metodo para calcular el IMC
public double calcularIMC(DatosNutricionales dn){
    double estaturaM = dn.getEstaturaCm()/100;
    return dn.getPesoKg() / (estaturaM * estaturaM);
}

//Metodo para calcular el IMC
public String clasificarIMC(double imc){
    if(imc < 18.5 ){
        return "Delgadez";
    }else if(imc >= 18.5 && imc <=24.9){
        return "Normopeso";
    }else if(imc >= 25 && imc <=29.9){
        return "Sobrepeso";
    }else if(imc >= 30 && imc <=34.9){
        return "Obesidad grado 1";
    }else if(imc >= 35&& imc <=39.9){
        return "Obesidad grado 2";
    }else{
        return "Obesidad morbida";
    }
}
}
