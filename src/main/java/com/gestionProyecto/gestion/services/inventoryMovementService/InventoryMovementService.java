package com.gestionProyecto.gestion.services.inventoryMovementService;

import com.gestionProyecto.gestion.dao.InventoryMovementRepository;
import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryMovementService implements IinventoryMovementService{
    @Autowired
    private InventoryMovementRepository repository;
    @Override
    public List<InventoryMovementsModel> getAllInventory() {
        return (List<InventoryMovementsModel>) repository.findAll();
    }
}
