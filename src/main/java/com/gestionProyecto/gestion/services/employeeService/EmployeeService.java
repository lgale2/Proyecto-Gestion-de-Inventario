package com.gestionProyecto.gestion.services.employeeService;

import com.gestionProyecto.gestion.dao.EmployeeRepository;
import com.gestionProyecto.gestion.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService implements IEmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeModel> getAll() {
        return (List<EmployeeModel>) employeeRepository.findAll();
    }

    @Override
    public EmployeeModel getById(Long id) {
        return (EmployeeModel) employeeRepository.findById(id).get();
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void save(EmployeeModel employeeModel) {
        employeeRepository.save(employeeModel);
    }

    @Override
    public void update(EmployeeModel employeeModel) {
        EmployeeModel existingEntity = getById(employeeModel.getId());
        if(existingEntity != null){
            existingEntity.setFirstname(employeeModel.getFirstname());
            existingEntity.setLastname(employeeModel.getLastname());
            existingEntity.setEmail(employeeModel.getEmail());
            existingEntity.setPhone(employeeModel.getPhone());
            existingEntity.setAddress(employeeModel.getAddress());
            existingEntity.setDateofadmission(employeeModel.getDateofadmission());
            existingEntity.setPosition(employeeModel.getPosition());
            employeeRepository.save(existingEntity);
        }
    }

    @Override
    public boolean exists(Long id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }
}
