package com.gestionProyecto.gestion.controllers;


import com.gestionProyecto.gestion.models.PositionModel;
import com.gestionProyecto.gestion.services.positionService.IPositionService;
import com.gestionProyecto.gestion.services.positionService.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {
    @Autowired
    private IPositionService service;
    @GetMapping("/api/positionsList")
    public List<PositionModel> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/positionById/{id}")
    public PositionModel getByIdPosition(@PathVariable String id)
    {
        return service.getIdByPosition(Long.parseLong(id));
    }

    @PostMapping("/api/position/save")
    public void save(@RequestBody PositionModel positionModel){
        service.save(positionModel);
    }

    @DeleteMapping("/api/position/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }
}
