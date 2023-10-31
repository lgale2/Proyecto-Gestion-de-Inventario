package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.dao.ProductRepository;
import com.gestionProyecto.gestion.models.ProductModel;
import com.gestionProyecto.gestion.services.productService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private IProductService service;

    @GetMapping("/api/productsList")

    public List<ProductModel>getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/api/productById/{id}")
    public ProductModel getById(@PathVariable String id){
        return service.getByIdProduct(Long.parseLong(id));
    }

    @PostMapping("/api/product/save")
    public void save(@RequestBody ProductModel productModel){
        service.save(productModel);
    }

    @DeleteMapping("/api/product/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }

}
