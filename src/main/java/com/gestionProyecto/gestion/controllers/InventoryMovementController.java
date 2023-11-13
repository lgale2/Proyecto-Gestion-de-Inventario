package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.EmployeeModel;
import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import com.gestionProyecto.gestion.models.ProductModel;
import com.gestionProyecto.gestion.services.employeeService.IEmployeeService;
import com.gestionProyecto.gestion.services.inventoryMovementService.IinventoryMovementService;
import com.gestionProyecto.gestion.services.productService.IProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Pattern;

@RestController
public class InventoryMovementController {
    @Autowired
    private IinventoryMovementService service;
    @Autowired
    private IProductService productService;
    @Autowired
    private IEmployeeService employeeService;

    @GetMapping("/api/inventoryMovementList")
    public List<InventoryMovementsModel>getAllInventoryMovement(){
        return service.getAllInventory();
    }

    @GetMapping("/api/inventoryMovementById/{id}")
    public InventoryMovementsModel getById(@PathVariable String id){
        if (id==null || !id.matches("\\d+")){
            throw new RuntimeException("El ID proporcionado no es valido.");
        }
        Long inventoryID = Long.parseLong(id);

        if (!service.exists(inventoryID)){
            throw new RuntimeException("No se encontró ningún registro de movimiento de inventario proporcionado con el ID");
        }

        return service.getById(Long.parseLong(id));
    }

    @PostMapping("/api/inventoryMovement/save")
    public void save(@RequestBody InventoryMovementsModel inventoryMovementsModel){

        ProductModel productoId = inventoryMovementsModel.getIdProducto();
        String movementType = inventoryMovementsModel.getMovementType();
        String movement_date = inventoryMovementsModel.getFechaMovimiento();
        String quantity = inventoryMovementsModel.getQuantity();
        String  unit_price = inventoryMovementsModel.getPrecioUnitario();
        String total = inventoryMovementsModel.getTotal();
        String invoice_reference = inventoryMovementsModel.getPreferenciaFactura();
        EmployeeModel id_employee = inventoryMovementsModel.getId_empleado();

        validateField(invoice_reference, "Factura Referencia", 3, 100, false, true);

        validateNumeric(quantity, "Cantidad", false);
        validateNumeric(unit_price, "Precio Unitario", true);
        validateNumeric(total, "Total", true);

        if (movementType == null || movementType.trim().isEmpty()) {
            throw new RuntimeException("El campo tipo de movimiento no puede estar vacío.");
        }

        // Validar que solo sea "E" o "S"
        if (!"E".equals(movementType.trim()) && !"S".equals(movementType.trim())) {
            throw new RuntimeException("El campo tipo de movimiento solo puede ser 'E' o 'S'.");
        }
        if (productoId == null || productoId.getIdProducto() == null || !productService.exists(productoId.getIdProducto())){
            throw new RuntimeException("El producto seleccionado no es válido.");
        }

        if (id_employee == null || id_employee.getId() == null || !employeeService.exists(id_employee.getId())){
            throw new RuntimeException("El empleado seleccionado no es válido.");
        }


        service.save(inventoryMovementsModel);
    }


    public void validateField(String fieldValue, String fieldName, int minLength, int maxLength, boolean allowEmpty,  boolean allowLetters){

        if (fieldValue == null || fieldValue.isEmpty()){
            if(!allowEmpty){
                throw new RuntimeException("No se permite dejar vacio el campo "+ fieldName);
            } else if (fieldValue.length()<minLength || fieldValue.length()>maxLength) {
                 throw new RuntimeException("La longitud del campo "+fieldName+ " debe estar entre " + minLength + " y " + maxLength);
            } else if (!allowLetters) {
                throw new RuntimeException("El campo " + fieldName + " no debe contener letras.");
            }
        }

    }

    public void validateNumeric(String fieldValue, String fieldName, boolean allowDecimal) {
        if (fieldValue == null) {
            throw new RuntimeException("No se permite dejar vacío el campo " + fieldName);
        }

        if (!isValidNumber(fieldValue.toString(), allowDecimal)) {
            throw new RuntimeException("El campo " + fieldName + " debe ser un número válido" + (allowDecimal ? " (entero o decimal)" : " (entero)"));
        }
    }

    private boolean isValidNumber(String value, boolean allowDecimal) {
        // Utiliza una expresión regular para verificar si la cadena es un número válido (entero o decimal)
        return allowDecimal ? value.matches("^\\d+(\\.\\d+)?$") : value.matches("^\\d+$");
    }


    @DeleteMapping("/api/inventoryMovement/delete/{id}")
    public void delete(@PathVariable String id){
        if (id==null || !id.matches("\\d+")){
            throw new RuntimeException("El ID proporcionado no es valido.");
        }
        Long inventoryID = Long.parseLong(id);

        if (!service.exists(inventoryID)){
            throw new RuntimeException("No se encontró ningún registro de movimiento de inventario proporcionado con el ID");
        }
        service.delete(Long.parseLong(id));
    }
    
    @PutMapping("/api/inventoryMovement/update/{id}")
    public void update(@PathVariable Long id, @RequestBody InventoryMovementsModel inventoryMovementsModel){
        InventoryMovementsModel existingInventory = service.getById(id);
        
        if(existingInventory != null){
            existingInventory.setIdProducto(inventoryMovementsModel.getIdProducto());
            existingInventory.setMovementType(inventoryMovementsModel.getMovementType());
            existingInventory.setFechaMovimiento(inventoryMovementsModel.getFechaMovimiento());
            existingInventory.setQuantity(inventoryMovementsModel.getQuantity());
            existingInventory.setPrecioUnitario(inventoryMovementsModel.getPrecioUnitario());
            existingInventory.setTotal(inventoryMovementsModel.getTotal());
            existingInventory.setPreferenciaFactura(inventoryMovementsModel.getPreferenciaFactura());
            existingInventory.setId_empleado(inventoryMovementsModel.getId_empleado());
            service.update(existingInventory);
        }
    }
}
