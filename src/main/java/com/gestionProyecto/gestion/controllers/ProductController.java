package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.dao.ProductRepository;
import com.gestionProyecto.gestion.models.CategoryModel;
import com.gestionProyecto.gestion.models.ProductModel;
import com.gestionProyecto.gestion.models.SupplierModel;
import com.gestionProyecto.gestion.services.categoryService.ICategoryService;
import com.gestionProyecto.gestion.services.productService.IProductService;
import com.gestionProyecto.gestion.services.supplierService.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class ProductController {
    @Autowired
    private IProductService service;

    @Autowired
    private ICategoryService serviceCategory;

    @Autowired
    private ISupplierService supplierService;

    @GetMapping("/api/productsList")

    public List<ProductModel>getAllProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/api/productById/{id}")
    public ProductModel getById(@PathVariable String id){
        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es valido");
        }

        Long idProduct = Long.parseLong(id);

        if(!service.exists(idProduct)){
            throw new RuntimeException("No se encontró ningún producto con el ID proporcionado");
        }

        return service.getByIdProduct(Long.parseLong(id));
    }

    @PostMapping("/api/product/save")
    public void save(@RequestBody ProductModel productModel){

        String product = productModel.getProducto();
        String description = productModel.getDescripcion();
        String purchasePriceString = String.valueOf(productModel.getPrecioCompra());
        String salePrice = String.valueOf(productModel.getPrecioVenta());
        String quantityStock = String.valueOf(productModel.getCantidadStock());
        CategoryModel idCategory = productModel.getIdCategoria();
        SupplierModel idSupplier = productModel.getIdProveedor();

        validateField(product, "Producto", 3, 40, false);
        validateField(description, "Descripcion", 3, 100, false);

       if (quantityStock == null || !quantityStock.matches("\\d+")) {
           throw new RuntimeException("El Precio compra proporcionado no es valido");
       }

        if (!isValidField(product) || !isValidField(description)){
            throw new RuntimeException("No se permiten numeros y caracteres en el campo.");
        }

        if (purchasePriceString == null || purchasePriceString.trim().isEmpty() || !isValidDoubleString(purchasePriceString) || !isNumeric(purchasePriceString)) {
            throw new RuntimeException("El campo Precio de Compra debe ser un número válido y no debe contener letras.");
        }

        if (salePrice == null || salePrice.trim().isEmpty() || !isValidDoubleString(salePrice) || !isNumeric(salePrice)) {
            throw new RuntimeException("El campo precio de Venta debe ser un número válido y no debe contener letras.");
        }

        if (quantityStock == null || quantityStock.trim().isEmpty() || !isValidDoubleString(quantityStock) || !isNumeric(quantityStock)) {
            throw new RuntimeException("El campo Cantidad en stock debe ser un número válido y no debe contener letras.");
        }

        if (idCategory== null || idCategory.getId_category() == null || serviceCategory.exists(idCategory.getId_category())){
            throw new RuntimeException("La categoria seleccionada no es valida.");
        }

        if (idSupplier== null || idSupplier.getIdSupplier() == null || supplierService.exists(idSupplier.getIdSupplier())){
            throw new RuntimeException("El proveedor seleccionado no es valido.");
        }



// Convertir el precioCompra a Double después de la validación
        Double purchasePrice = Double.parseDouble(purchasePriceString);
// Asignar el valor validado a productModel
        productModel.setPrecioCompra(purchasePrice);
        service.save(productModel);
    }


    private boolean isValidDoubleString(String value) {
        // Utilizar una expresión regular para verificar que la cadena representa un número válido
        // El patrón permite números decimales positivos o negativos
        String decimalPattern = "^[+-]?([0-9]*[.])?[0-9]+$";
        return Pattern.matches(decimalPattern, value);
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }



    @PutMapping("/api/product/update/{id}")
    public void update(@PathVariable String id, @RequestBody ProductModel productModel){


        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es valido");
        }

        Long idProduct = Long.parseLong(id);

        if(!service.exists(idProduct)){
            throw new RuntimeException("No se encontró ningún producto con el ID proporcionado");
        }

        ProductModel existingProduct = service.getByIdProduct(Long.parseLong(id));

        
        if(existingProduct != null){

            String product = productModel.getProducto();
            String description = productModel.getDescripcion();
            String purchasePriceString = String.valueOf(productModel.getPrecioCompra());
            String salePrice = String.valueOf(productModel.getPrecioVenta());
            String quantityStock = String.valueOf(productModel.getCantidadStock());
            CategoryModel idCategory = productModel.getIdCategoria();
            SupplierModel idSupplier = productModel.getIdProveedor();

            validateField(product, "Producto", 3, 40, false);
            validateField(description, "Descripcion", 3, 100, false);

            if (quantityStock == null || !quantityStock.matches("\\d+")) {
                throw new RuntimeException("El Precio compra proporcionado no es valido");
            }

            if (!isValidField(product) || !isValidField(description)){
                throw new RuntimeException("No se permiten numeros y caracteres en el campo.");
            }

            if (purchasePriceString == null || purchasePriceString.trim().isEmpty() || !isValidDoubleString(purchasePriceString) || !isNumeric(purchasePriceString)) {
                throw new RuntimeException("El campo Precio de Compra debe ser un número válido y no debe contener letras.");
            }

            if (salePrice == null || salePrice.trim().isEmpty() || !isValidDoubleString(salePrice) || !isNumeric(salePrice)) {
                throw new RuntimeException("El campo precio de Venta debe ser un número válido y no debe contener letras.");
            }

            if (quantityStock == null || quantityStock.trim().isEmpty() || !isValidDoubleString(quantityStock) || !isNumeric(quantityStock)) {
                throw new RuntimeException("El campo Cantidad en stock debe ser un número válido y no debe contener letras.");
            }

            if (idCategory== null || idCategory.getId_category() == null || serviceCategory.exists(idCategory.getId_category())){
                throw new RuntimeException("La categoria seleccionada no es valida.");
            }

            if (idSupplier== null || idSupplier.getIdSupplier() == null || supplierService.exists(idSupplier.getIdSupplier())){
                throw new RuntimeException("El proveedor seleccionado no es valido.");
            }



// Convertir el precioCompra a Double después de la validación
            Double purchasePrice = Double.parseDouble(purchasePriceString);
// Asignar el valor validado a productModel
            productModel.setPrecioCompra(purchasePrice);



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

    public void validateField(String fieldValue, String fieldName, int minLength, int maxLength, boolean allowEmpty){
        if (fieldValue == null || fieldValue.isEmpty()){
            if (!allowEmpty){
                throw new RuntimeException("No se permite dejar el campo vacio "+fieldName);
            } else if (fieldValue.length()<minLength || fieldValue.length()>maxLength) {
                throw new RuntimeException("La longitud del campo debe estar entre " + minLength + " y " + maxLength + " caracteres");
            }
        }
    }


    private boolean isValidField(String value){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        return pattern.matcher(value).matches();
    }

    @DeleteMapping("/api/product/delete/{id}")
    public void delete(@PathVariable String id){

        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es valido");
        }

        Long idProduct = Long.parseLong(id);

        if(!service.exists(idProduct)){
            throw new RuntimeException("No se encontró ningún producto con el ID proporcionado");
        }

        service.delete(Long.parseLong(id));
    }

}
