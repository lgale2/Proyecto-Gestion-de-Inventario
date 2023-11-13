package com.gestionProyecto.gestion.services.productService;

import com.gestionProyecto.gestion.models.ProductModel;

import java.util.List;

public interface IProductService {
    List<ProductModel>getAllProducts();

    ProductModel getByIdProduct(Long id);

    void save(ProductModel productModel);

    void update(ProductModel productModel);
    void delete(Long id);

    boolean exists(Long idProducto);
}
