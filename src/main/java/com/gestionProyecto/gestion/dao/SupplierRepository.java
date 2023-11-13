package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.SupplierModel;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepository extends CrudRepository<SupplierModel, Long> {
    boolean existsByEmail(String email);
}
