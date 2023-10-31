package com.gestionProyecto.gestion.services.userService;

import com.gestionProyecto.gestion.models.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> getAll();
    UserModel getByIdUser(Long id);

    void save(UserModel userModel);
    void delete(Long id);
}
