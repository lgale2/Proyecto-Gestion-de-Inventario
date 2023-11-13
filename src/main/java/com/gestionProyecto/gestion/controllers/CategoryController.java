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
        if (id == null || !id.matches("\\d+")){
            throw new RuntimeException("El id proporcionado no es valido.");
        }

        Long categoryId = Long.parseLong(id);

        if (!service.exists(categoryId)){
            throw new RuntimeException("No se encontró ninguna categoria con el ID proporcionado. ");
        }
        return service.getByIdCategory(Long.parseLong(id));
    }

    @PostMapping("/api/categories/save")
    public void save(@RequestBody CategoryModel categoryModel){

        String category = categoryModel.getCategory();


       validateField(category, "Categoria", 3, 40, false, true);


        if (!isValidField(category) || !isValidField(category)){
            throw new RuntimeException("No se permiten numeros y caracteres en el campo categoria.");
        }

        if(service.existsCategory(category)){
            throw new RuntimeException("La categoria ya esta registrada.");
        }


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

        if (id == null || !id.matches("\\d+")){
            throw new RuntimeException("El ID proporcionado no es valido.");
        }

        Long categoryId = Long.parseLong(id);
        if (!service.exists(categoryId)){
            throw new RuntimeException("No se encontro ninguna categoria con el ID proporcionado");
        }


        service.delete(Long.parseLong(id));
    }

    @PutMapping("/api/categories/update/{id}")
    public void update(@PathVariable String id, @RequestBody CategoryModel categoryModel){

        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es válido.");
        }

        Long categoryId = Long.parseLong(id);

        if (!service.exists(categoryId)) {
            throw new RuntimeException("No se encontró ninguna categoria con el ID proporcionado.");
        }

        CategoryModel existingCategory = service.getByIdCategory(Long.parseLong(id));
        if(existingCategory != null){

            String category = categoryModel.getCategory();
            validateField(category, "Categoria", 3, 40, false, true);
            existingCategory.setCategory(categoryModel.getCategory());
            if (!isValidField(category) || !isValidField(category)){
                throw new RuntimeException("No se permiten numeros y caracteres en el campo categoria.");
            }

            service.update(existingCategory);
        }


    }


}
