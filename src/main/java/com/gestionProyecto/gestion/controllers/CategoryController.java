package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.dao.CategoryRepository;
import com.gestionProyecto.gestion.models.CategoryModel;
import com.gestionProyecto.gestion.services.categoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService service;

    @Autowired
    private CategoryRepository repository;

    @GetMapping("/api/categoriesList")
    public List<CategoryModel>getAllCategory(){
        return service.getAllCategory();
    }

    @GetMapping("/api/categoryById/{id}")
    public CategoryModel getById(@PathVariable String id){
        return service.getByIdCategory(Long.parseLong(id));
    }

    @PostMapping("/api/categories/save")
    public void save(@RequestBody CategoryModel categoryModel){

        String category = categoryModel.getCategory();


            if (category == null || category.isEmpty()){
                throw new RuntimeException("No se permite dejar vacio el campo categoria");
            } else if (category.length()>40) {
                throw new RuntimeException("No se permite excederse mas de 50 caracteres el campo categoria");
            } else if (!isValidField(category)) {
                throw new RuntimeException("No se permiten numeros y caracteres especiales en el campo categoria");
            }
            if (repository.existsByCategory(category)){
                throw new RuntimeException("La categoria ya existe");
            }


        service.save(categoryModel);
    }

    private boolean isValidField(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(value).matches();
    }

    @DeleteMapping("/api/categories/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }

    @PutMapping("/api/categories/update/{id}")
    public void update(@PathVariable Long id, @RequestBody CategoryModel categoryModel){
        CategoryModel existingCategory = service.getByIdCategory(id);
        if(existingCategory != null){
            existingCategory.setCategory(categoryModel.getCategory());
            service.update(existingCategory);
        }


    }


}
