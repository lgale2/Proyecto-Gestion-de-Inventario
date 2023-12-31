package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.UserRoleModel;
import com.gestionProyecto.gestion.services.userroleService.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRoleController {
    @Autowired
    private IUserRoleService service;

    @GetMapping("/api/getAllUserRole")
    public List<UserRoleModel>getAll(){
        return (List<UserRoleModel>) service.getAll();
    }

    @GetMapping("/api/getByIdUserRole/{id}")
    public UserRoleModel getById(@PathVariable String id){
        return service.getById(Long.parseLong(id));
    }

    @PostMapping("/api/userRole/save")
    public void save(@RequestBody UserRoleModel userRoleModel){
        service.save(userRoleModel);
    }

    @PutMapping("/api/userRole/update/{id}")
    public void update(@PathVariable Long id, @RequestBody UserRoleModel userRoleModel){
        UserRoleModel existingUserRole = service.getById(id);
        
        if(existingUserRole != null){
            existingUserRole.setIdUser(userRoleModel.getIdUser());
            existingUserRole.setIdRole(userRoleModel.getIdRole());
            service.update(existingUserRole);
        }
    }
    
    @DeleteMapping("/api/userRole/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }
}
