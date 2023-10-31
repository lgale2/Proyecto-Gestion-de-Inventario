package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.CustomerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerModel, Long> {
}
