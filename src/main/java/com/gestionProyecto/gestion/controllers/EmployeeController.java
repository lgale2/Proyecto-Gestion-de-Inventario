package com.gestionProyecto.gestion.controllers;


import com.gestionProyecto.gestion.models.EmployeeModel;

import com.gestionProyecto.gestion.models.PositionModel;
import com.gestionProyecto.gestion.services.employeeService.IEmployeeService;
import com.gestionProyecto.gestion.services.positionService.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class EmployeeController {



    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IPositionService positionService;




    @GetMapping("/api/employeesList")
    public List<EmployeeModel> getAll(){
        return employeeService.getAll();
  }

    @GetMapping("/api/employees/{id}")
    public EmployeeModel getById( @PathVariable String id){
        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es válido.");
        }

        Long employeeId = Long.parseLong(id);

        if (!employeeService.exists(employeeId)) {
            throw new RuntimeException("No se encontró ningún empleado con el ID proporcionado.");
        }
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

        validateField(firstName, "Nombre", 3, 40, false, true);
        validateField(lastName, "Apellido", 3, 40, false, true);
        validateField(email, "Correo Electronico", 10, 255, false, true);
        validateField(phone, "Telefono", 8, 8, false, false);
        validateField(address, "Direccion", 4, 100, false, true);


         if (!isValidField(firstName) || !isValidField(lastName)){
             throw new RuntimeException("No se permiten numeros y caracteres en el campo.");
         }
         if (!isValidEmail(email)) {
             throw new RuntimeException("El correo electronico no es valido");
         }

         if (position == null || position.getIdPosition() == null || !positionService.exists(position.getIdPosition())){
             throw new RuntimeException("La posición seleccionada no es válida.");
         }

        employeeService.save(employeeModel);
    }

    private void validateField(String fieldValue, String fieldName, int minLength, int maxLength, boolean allowEmpty, boolean allowLetters){
        if(fieldValue == null || fieldValue.isEmpty()){
            if (!allowEmpty){
                throw new RuntimeException("No se permite dejar vacio el campo "+ fieldName);
            }
        } else if (fieldValue.length()<minLength || fieldValue.length()>maxLength ) {

            throw new RuntimeException("La longitud del campo " + fieldName + " debe estar entre " + minLength + " y " + maxLength + " caracteres.");
        } else if (!allowLetters && !isNumeric(fieldValue)) {
            throw new RuntimeException("El campo " + fieldName + " no debe contener letras.");

        }
    }

    private boolean isValidField(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(value).matches();
    }

    private boolean isNumeric(String value){
        try {
            Long.parseLong(value);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return  matcher.matches();
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
