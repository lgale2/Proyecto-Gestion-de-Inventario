package com.gestionProyecto.gestion.controllers;


import com.gestionProyecto.gestion.models.PositionModel;
import com.gestionProyecto.gestion.services.positionService.IPositionService;
import com.gestionProyecto.gestion.services.positionService.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;


@RestController
public class PositionController {
    @Autowired
    private IPositionService service;
    @GetMapping("/api/positionsList")
    public List<PositionModel> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/positionById/{id}")
    public PositionModel getByIdPosition(@PathVariable String id)
    {
        if (id == null || !id.matches("\\d+")){
            throw new RuntimeException("El ID proporcionado no es valido.");
        }

        Long positionId = Long.parseLong(id);
        if (!service.exists(positionId)){
            throw new RuntimeException("No se encontro ninguna posicion con el ID proporcionado");
        }


        return service.getIdByPosition(Long.parseLong(id));
    }

    @PostMapping("/api/position/save")
    public void save(@RequestBody PositionModel positionModel){

        String position = positionModel.getPosition();

        validateField(position, "Posicion", 3, 40, false);


        if (!isValidField(position) || !isValidField(position)){
            throw new RuntimeException("No se permiten numeros y caracteres en el campo posicion.");
        }

        if(service.existsPosition(position)){
            throw new RuntimeException("La posicion ya esta registrada.");
        }


        service.save(positionModel);
    }



    private void validateField(String fieldValue, String fieldName, int minLength, int maxLength, boolean allowEmty){
        if (fieldValue == null || fieldValue.isEmpty()){
            if (!allowEmty){
                throw new RuntimeException("No se permite dejar el campo "+fieldName+ " vacio.");
            } else if (fieldValue.length()<minLength || fieldValue.length()>maxLength) {

                throw new RuntimeException("La longitud del campo "+fieldName+ " debe estar entre "+minLength + " y " + maxLength);
            }
        }
    }

    private boolean isValidField(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(value).matches();
    }


    @PutMapping("/api/position/update/{id}")
    public void update(@PathVariable String id, @RequestBody PositionModel positionModel){

        if (id == null || !id.matches("\\d+")){
            throw new RuntimeException("El ID proporcionado no es valido.");
        }

        Long positionId = Long.parseLong(id);
        if (!service.exists(positionId)){
            throw new RuntimeException("No se encontro ninguna posicion con el ID proporcionado");
        }



        PositionModel existingPosition = service.getIdByPosition(Long.parseLong(id));

        if(existingPosition != null){
            String position = positionModel.getPosition();

            validateField(position, "Posicion", 3, 40, false);


            if (!isValidField(position) || !isValidField(position)){
                throw new RuntimeException("No se permiten numeros y caracteres en el campo posicion.");
            }

            if(service.existsPosition(position)){
                throw new RuntimeException("La posicion ya esta registrada.");
            }

            existingPosition.setPosition(positionModel.getPosition());
            service.update(existingPosition);
        }
    }

    @DeleteMapping("/api/position/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }
}
