package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.InventoryMovementsModel;
import org.springframework.data.repository.CrudRepository;

public interface InventoryMovementRepository extends CrudRepository<InventoryMovementsModel,Long> {
}
