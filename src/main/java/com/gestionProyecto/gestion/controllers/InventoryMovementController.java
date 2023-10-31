package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import com.gestionProyecto.gestion.services.inventoryMovementService.IinventoryMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryMovementController {
    @Autowired
    private IinventoryMovementService service;

    @GetMapping("/api/inventoryMovementList")
    public List<InventoryMovementsModel>getAllInventoryMovement(){
        return service.getAllInventory();
    }
}
