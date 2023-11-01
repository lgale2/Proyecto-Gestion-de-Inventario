package com.gestionProyecto.gestion.services.productService;

import com.gestionProyecto.gestion.dao.ProductRepository;
import com.gestionProyecto.gestion.models.ProductModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{
    @Autowired
    private ProductRepository repository;
    @Override
    public List<ProductModel> getAllProducts() {
        return (List<ProductModel>) repository.findAll();
    }

    @Override
    public ProductModel getByIdProduct(Long id) {
        return (ProductModel) repository.findById(id).get();
    }

    @Override
    public void save(ProductModel productModel) {
        repository.save(productModel);
    }

    @Override
    public void update(ProductModel productModel) {
        ProductModel existingEntity = getByIdProduct(productModel.getIdProducto());

        if(existingEntity!=null){
            existingEntity.setProducto(productModel.getProducto());
            existingEntity.setDescripcion(productModel.getDescripcion());
            existingEntity.setPrecioCompra(productModel.getPrecioCompra());
            existingEntity.setPrecioVenta(productModel.getPrecioVenta());
            existingEntity.setCantidadStock(productModel.getCantidadStock());
            existingEntity.setIdCategoria(productModel.getIdCategoria());
            existingEntity.setIdProveedor(productModel.getIdProveedor());
            repository.save(existingEntity);

        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
