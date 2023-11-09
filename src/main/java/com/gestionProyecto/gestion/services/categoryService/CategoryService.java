package com.gestionProyecto.gestion.services.categoryService;

import com.gestionProyecto.gestion.dao.CategoryRepository;
import com.gestionProyecto.gestion.models.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository repository;
    @Override
    public List<CategoryModel> getAllCategory() {

        return (List<CategoryModel>) repository.findAll();
    }

    @Override
    public CategoryModel getByIdCategory(Long id) {
        return (CategoryModel) repository.findById(id).get();
    }

    @Override
    public void save(CategoryModel categoryModel) {
        repository.save(categoryModel);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(CategoryModel categoryModel) {
        CategoryModel existingEntity = getByIdCategory(categoryModel.getId_category());
        if(existingEntity != null){
            existingEntity.setCategory(categoryModel.getCategory());
            repository.save(existingEntity);
        }
    }

    @Override
    public boolean exists(Long categoryId) {
        return repository.existsById(categoryId);
    }


}
