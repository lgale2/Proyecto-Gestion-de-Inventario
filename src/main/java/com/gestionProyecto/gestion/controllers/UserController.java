package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.UserModel;
import com.gestionProyecto.gestion.services.userService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private IUserService service;

    @GetMapping("/api/getAllUsers")
    public List<UserModel> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/getByIdUser/{id}")
    public UserModel getById(@PathVariable String id){
        return service.getByIdUser(Long.parseLong(id));
    }

    @PostMapping("/api/user/save")
    public void save(@RequestBody UserModel userModel){
        service.save(userModel);
    }

    @DeleteMapping("/api/user/delete/{id}")
    public void delete(@PathVariable String id){
        service.delete(Long.parseLong(id));
    }

}
