package com.gestionProyecto.gestion.services.userroleService;

import com.gestionProyecto.gestion.models.UserRoleModel;

import java.util.List;

public interface IUserRoleService {
    public List<UserRoleModel> getAll();

    public UserRoleModel getById(Long id);

    public void save(UserRoleModel userRoleModel);

    public void update(UserRoleModel userRoleModel);

    public void delete(Long id);
}
