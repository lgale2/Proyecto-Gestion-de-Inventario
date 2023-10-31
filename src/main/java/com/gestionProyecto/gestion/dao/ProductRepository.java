package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.ProductModel;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductModel, Long> {
}
