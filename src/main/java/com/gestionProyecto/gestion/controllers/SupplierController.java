package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.SupplierModel;
import com.gestionProyecto.gestion.services.supplierService.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupplierController {
    @Autowired
    private ISupplierService service;

    @GetMapping("/api/suppliersList")
    public List<SupplierModel>getAllSuppliers(){
        return (List<SupplierModel>) service.getAllSuppliers();
    }

    @GetMapping("/api/supplierById/{id}")
    public SupplierModel SuppliergetById(@PathVariable String id){
        return service.getByIdSupplier(Long.parseLong(id));
    }

    @PostMapping("/api/supplier/save")
    public void save(@RequestBody SupplierModel supplierModel){
        service.save(supplierModel);
    }

    @DeleteMapping("/api/supplier/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }
}
