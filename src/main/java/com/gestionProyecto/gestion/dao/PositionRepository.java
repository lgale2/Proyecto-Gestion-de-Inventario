package com.gestionProyecto.gestion.dao;


import com.gestionProyecto.gestion.models.PositionModel;
import org.springframework.data.repository.CrudRepository;

public interface PositionRepository extends CrudRepository<PositionModel, Long> {
}
