package com.gestionProyecto.gestion.controllers;


import com.gestionProyecto.gestion.models.EmployeeModel;

import com.gestionProyecto.gestion.services.employeeService.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class EmployeeController {



    @Autowired
    private IEmployeeService employeeService;



    @GetMapping("/api/employeesList")
    public List<EmployeeModel> getAll(){
        return employeeService.getAll();
  }




    @GetMapping("/api/employees/{id}")
    public EmployeeModel getById( @PathVariable String id){
        return employeeService.getById(Long.parseLong(id));
    }


    @DeleteMapping("/api/employees/delete/{id}")
    public void remove(@PathVariable String id){
        employeeService.remove(Long.parseLong(id));
    }

    //guardar
    @PostMapping("/api/employees/save")
    public void save(@RequestBody EmployeeModel employeeModel){

        if(employeeModel != null){
            if(employeeModel.getFirstname() == null || employeeModel.getFirstname().isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo nombre");
            }
            else if(employeeModel.getFirstname().length()>40){
                throw new IllegalArgumentException("No se permite excederse mas de 40 caracteres");
            }
            else if(!isValidField(employeeModel.getFirstname()) && !isValidField(employeeModel.getLastname())){
                throw new IllegalArgumentException("No se permite numeros");
            }

        }

//        // Obtén la posición por su ID
//        PositionModel position = positionService.getById(employeeModel.getPosition());
//        if (position == null) {
//            throw new IllegalArgumentException("La posición seleccionada no es válida.");
//        }
//
//        // Asigna el ID de la posición al empleado
//        employeeModel.setPosition(position.getIdPosition());

        // Asignar el ID de la posición al empleado
//
//        PositionModel position = positionService.getPositionByPosition(employeeModel.getPosition());
//        if (position == null) {
//            throw new IllegalArgumentException("La posición seleccionada no es válida.");
//        }
//
//        // Asignar el ID de la posición al empleado
//        employeeModel.setPosition(position.getIdPosition());
        employeeService.save(employeeModel);
    }

    private boolean isValidField(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(value).matches();
    }
}
