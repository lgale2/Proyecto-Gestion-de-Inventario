package com.gestionProyecto.gestion.services.categoryService;

import com.gestionProyecto.gestion.models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> getAllCategory();
    CategoryModel getByIdCategory(Long id);

    void save(CategoryModel categoryModel);
    void delete(Long id);

    void update(CategoryModel categoryModel);

    boolean exists(Long categoryId);

    boolean existsCategory(String category);
}
