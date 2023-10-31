package com.gestionProyecto.gestion.services.customerService;

import com.gestionProyecto.gestion.models.CustomerModel;

import java.util.List;

public interface ICustomerService {
    List<CustomerModel> getAll();

    CustomerModel getById(Long id);

    void remove(Long id);

    void save(CustomerModel customerModel);
}
