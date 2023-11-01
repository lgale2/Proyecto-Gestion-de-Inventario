package com.gestionProyecto.gestion.controllers;


import com.gestionProyecto.gestion.models.EmployeeModel;

import com.gestionProyecto.gestion.models.PositionModel;
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

        String firstName = employeeModel.getFirstname();
        String lastName = employeeModel.getLastname();
        String email = employeeModel.getEmail();
        String phone = employeeModel.getPhone();
        String address = employeeModel.getAddress();
        String date = employeeModel.getDateofadmission();
        PositionModel position = employeeModel.getPosition();


            if(firstName == null || firstName.isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo nombre");
            }
            else if(firstName.length()>40){
                throw new IllegalArgumentException("No se permite excederse mas de 40 caracteres");
            }

            else if(lastName == null || lastName.isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo apellido");
            }
            else if(lastName.length()>40){
                throw new IllegalArgumentException("No se permite excederse mas de 40 caracteres");
            }

            else if(email == null || email.isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo correo");
            }
            else if(email.length()<10 || email.length()>255){
                throw new IllegalArgumentException("No se permite excederse mas de 255 caracteres y menos de 10 caracteres");
            }

            else if(phone == null || phone.isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo telefono");
            }
            else if(phone.length() != 8){
                throw new IllegalArgumentException("La longitud del campo telefono debe ser exactamente de 8 caracteres");
            }

            else if(address == null || address.isEmpty()){
                throw new IllegalArgumentException("No se permite dejar vacio el campo direccion");
            }
            else if(address.length()>100){
                throw new IllegalArgumentException("No se permite excederse mas de 100 caracteres");
            }

            else if(position == null ){
                throw new IllegalArgumentException("No se permite dejar vacio el campo posicion");
            }

            else if(!isValidField(firstName) || !isValidField(lastName)){
                throw new IllegalArgumentException("No se permite numeros y caracteres");
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

    @PutMapping("/api/employee/update/{id}")
    public void update(@PathVariable Long id, @RequestBody EmployeeModel employeeModel){
        EmployeeModel existingEmployee = employeeService.getById(id);
        if(existingEmployee!= null){
            existingEmployee.setFirstname(employeeModel.getFirstname());
            existingEmployee.setLastname(employeeModel.getLastname());
            existingEmployee.setEmail(employeeModel.getEmail());
            existingEmployee.setPhone(employeeModel.getPhone());
            existingEmployee.setAddress(employeeModel.getAddress());
            existingEmployee.setDateofadmission(employeeModel.getDateofadmission());
            existingEmployee.setPosition(employeeModel.getPosition());
            employeeService.update(existingEmployee);
        }
    }
}
