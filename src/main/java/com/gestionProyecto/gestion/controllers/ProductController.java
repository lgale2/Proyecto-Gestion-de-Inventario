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

    @PutMapping("/api/product/update/{id}")
    public void update(@PathVariable Long id, @RequestBody ProductModel productModel){
        ProductModel existingProduct = service.getByIdProduct(id);
        
        if(existingProduct != null){
            existingProduct.setProducto(productModel.getProducto());
            existingProduct.setDescripcion(productModel.getDescripcion());
            existingProduct.setPrecioCompra(productModel.getPrecioCompra());
            existingProduct.setPrecioVenta(productModel.getPrecioVenta());
            existingProduct.setCantidadStock(productModel.getCantidadStock());
            existingProduct.setIdCategoria(productModel.getIdCategoria());
            existingProduct.setIdProveedor(productModel.getIdProveedor());
            service.update(existingProduct);
        }
    }
    
    
    @DeleteMapping("/api/product/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }

}
