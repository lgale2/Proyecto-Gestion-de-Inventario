package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import com.gestionProyecto.gestion.services.inventoryMovementService.IinventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryMovementController {
    @Autowired
    private IinventoryMovementService service;

    @GetMapping("/api/inventoryMovementList")
    public List<InventoryMovementsModel>getAllInventoryMovement(){
        return service.getAllInventory();
    }

    @GetMapping("/api/inventoryMovementById/{id}")
    public InventoryMovementsModel getById(@PathVariable String id){
        return service.getById(Long.parseLong(id));
    }

    @PostMapping("/api/inventoryMovement/save")
    public void save(@RequestBody InventoryMovementsModel inventoryMovementsModel){
        service.save(inventoryMovementsModel);
    }

    @DeleteMapping("/api/inventoryMovement/delete/{id}")
    public void delete(@PathVariable String id){
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
