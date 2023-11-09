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


       validateField(category, "Categoria", 3, 40, false, true);


        service.save(categoryModel);
    }

    private void validateField(String fieldValue, String fieldName, int minLength, int maxLength, boolean allowEmty, boolean allowLetters){
        if (fieldValue == null || fieldValue.isEmpty()){
            if (!allowEmty){
                throw new RuntimeException("No se permite dejar el campo "+fieldName+ " vacio.");
            } else if (fieldValue.length()<minLength || fieldValue.length()>maxLength) {

                throw new RuntimeException("La longitud del campo "+fieldName+ " debe estar entre "+minLength + " y " + maxLength);
            }
        }
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
