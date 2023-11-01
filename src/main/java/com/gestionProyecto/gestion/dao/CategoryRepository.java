package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.CategoryModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryModel, Long> {

    boolean existsByCategory(String category);

}
