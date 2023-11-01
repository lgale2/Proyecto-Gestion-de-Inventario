package com.gestionProyecto.gestion.services.employeeService;

import com.gestionProyecto.gestion.models.EmployeeModel;

import java.util.List;

public interface IEmployeeService {
    List<EmployeeModel> getAll();


    EmployeeModel getById(Long id);


    void remove(Long id);

    void save(EmployeeModel employeeModel);

    void update(EmployeeModel employeeModel);
}
