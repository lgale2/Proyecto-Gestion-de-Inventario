package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.CustomerModel;
import com.gestionProyecto.gestion.services.customerService.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

   @Autowired
   private ICustomerService service;
    @GetMapping("/api/customersList")
    public List<CustomerModel> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/customers/{id}")
    public CustomerModel getById( @PathVariable String id){
        return service.getById(Long.parseLong(id));
    }


    @DeleteMapping("/api/customers/delete/{id}")
    public void remove(@PathVariable String id){
       service.remove(Long.parseLong(id));
    }

    //guardar
    @PostMapping("/api/customers/save")
    public void save(@RequestBody CustomerModel customerModel){
        service.save(customerModel);
    }
}
