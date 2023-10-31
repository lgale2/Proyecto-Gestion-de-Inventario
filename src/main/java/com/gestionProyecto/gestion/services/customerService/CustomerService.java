package com.gestionProyecto.gestion.services.customerService;

import com.gestionProyecto.gestion.dao.CustomerRepository;
import com.gestionProyecto.gestion.models.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<CustomerModel> getAll(){
       return (List<CustomerModel>) repository.findAll();
    }

    @Override
    public CustomerModel getById(Long id) {
        return (CustomerModel) repository.findById(id).get();
    }

    //eliminar
    @Override
    public void remove(Long id){
        repository.deleteById(id);
    }

    //guardar
    @Override
    public void save(CustomerModel customerModel){
        repository.save(customerModel);
    }

//    //actualizar
//    @Override
//    public void update(Long id){
//        repository.update(id);
//    }
}
