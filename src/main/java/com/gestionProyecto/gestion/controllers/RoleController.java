package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.RoleModel;
import com.gestionProyecto.gestion.services.roleService.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private IRoleService service;

    @GetMapping("/api/rolesList")
    public List<RoleModel>getAll(){
        return service.getAllRoles();
    }

    @GetMapping("/api/roleById/{id}")
    public RoleModel getByIdPosition(@PathVariable String id){
        return service.getByIdRole(Long.parseLong(id));
    }

    @PostMapping("/api/role/save")
    public void save(@RequestBody RoleModel roleModel){
        service.save(roleModel);
    }

    @DeleteMapping("/api/role/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }

    @PutMapping("/api/role/update/{id}")
    public void update(@PathVariable Long id, @RequestBody RoleModel roleModel){
        RoleModel existingRole = service.getByIdRole(id);

        if (existingRole != null) {
            existingRole.setRole(roleModel.getRole());
            service.update(existingRole);
        }
    }
}
