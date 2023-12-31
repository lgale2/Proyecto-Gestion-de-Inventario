package com.gestionProyecto.gestion.services.positionService;

import com.gestionProyecto.gestion.dao.CustomerRepository;
import com.gestionProyecto.gestion.dao.PositionRepository;
import com.gestionProyecto.gestion.models.CustomerModel;
import com.gestionProyecto.gestion.models.PositionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PositionService implements IPositionService{
    @Autowired
    private PositionRepository repository;

    @Override
    public List<PositionModel> getAll(){
        return (List<PositionModel>) repository.findAll();
    }

    @Override
    public PositionModel getIdByPosition(Long id) {
        return (PositionModel) repository.findById(id).get();
    }

    @Override
    public void save(PositionModel positionModel) {
        repository.save(positionModel);
    }

    @Override
    public void update(PositionModel positionModel) {
        PositionModel existingEntity = getIdByPosition(positionModel.getIdPosition());
        if(existingEntity != null){
            existingEntity.setPosition(positionModel.getPosition());
            repository.save(existingEntity);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long idPosition) {
        return repository.existsById(idPosition);
    }

    @Override
    public boolean existsPosition(String position) {
        return repository.existsByPosition(position);
    }
}
