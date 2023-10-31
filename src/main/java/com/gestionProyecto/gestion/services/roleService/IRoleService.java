package com.gestionProyecto.gestion.services.roleService;

import com.gestionProyecto.gestion.models.RoleModel;


import java.util.List;

public interface IRoleService{
    List<RoleModel> getAllRoles();
    RoleModel getByIdRole(Long id);

    void save(RoleModel roleModel);

    void delete(Long id);

    void update(RoleModel roleModel);
}
