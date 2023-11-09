package com.gestionProyecto.gestion.dao;
import com.gestionProyecto.gestion.models.EmployeeModel;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<EmployeeModel, Long> {

    boolean existsByEmail(String email);
}
