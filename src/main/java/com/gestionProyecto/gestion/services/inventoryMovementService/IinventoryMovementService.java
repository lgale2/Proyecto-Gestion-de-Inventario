package com.gestionProyecto.gestion.services.inventoryMovementService;

import com.gestionProyecto.gestion.dao.InventoryMovementRepository;
import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface IinventoryMovementService {


     List<InventoryMovementsModel> getAllInventory();
     InventoryMovementsModel getById(Long id);
     void save(InventoryMovementsModel inventoryMovementsModel);

     void delete(Long id);

     void update(InventoryMovementsModel inventoryMovementsModel);

    boolean exists(Long inventoryID);
}
