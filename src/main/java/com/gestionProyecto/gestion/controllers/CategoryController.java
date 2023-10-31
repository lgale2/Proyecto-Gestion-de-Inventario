package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.CategoryModel;
import com.gestionProyecto.gestion.services.categoryService.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private ICategoryService service;

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
        service.save(categoryModel);
    }

    @DeleteMapping("/api/categories/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }


}
