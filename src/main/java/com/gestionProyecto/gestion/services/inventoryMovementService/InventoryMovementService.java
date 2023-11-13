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

    @Override
    public InventoryMovementsModel getById(Long id) {
        return (InventoryMovementsModel) repository.findById(id).get();
    }

    @Override
    public void save(InventoryMovementsModel inventoryMovementsModel) {
        repository.save(inventoryMovementsModel);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(InventoryMovementsModel inventoryMovementsModel) {
        InventoryMovementsModel existingEntity = getById(inventoryMovementsModel.getId_movimiento());
        if(existingEntity != null){
            existingEntity.setIdProducto(inventoryMovementsModel.getIdProducto());
            existingEntity.setMovementType(inventoryMovementsModel.getMovementType());
            existingEntity.setFechaMovimiento(inventoryMovementsModel.getFechaMovimiento());
            existingEntity.setQuantity(inventoryMovementsModel.getQuantity());
            existingEntity.setPrecioUnitario(inventoryMovementsModel.getPrecioUnitario());
            existingEntity.setTotal(inventoryMovementsModel.getTotal());
            existingEntity.setPreferenciaFactura(inventoryMovementsModel.getPreferenciaFactura());
            existingEntity.setId_empleado(inventoryMovementsModel.getId_empleado());
            repository.save(existingEntity);
        }
    }

    @Override
    public boolean exists(Long inventoryID) {
        return repository.existsById(inventoryID);
    }
}
