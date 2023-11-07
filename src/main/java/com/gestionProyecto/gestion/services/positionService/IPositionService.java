package com.gestionProyecto.gestion.services.positionService;

import com.gestionProyecto.gestion.models.CustomerModel;
import com.gestionProyecto.gestion.models.PositionModel;

import java.util.List;

public interface IPositionService {
    List<PositionModel> getAll();

    PositionModel getIdByPosition(Long id);

    void save(PositionModel positionModel);

    void update(PositionModel positionModel);

    void delete(Long id);

    boolean exists(Long idPosition);
}
