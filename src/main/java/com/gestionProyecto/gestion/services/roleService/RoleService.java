package com.gestionProyecto.gestion.services.roleService;

import com.gestionProyecto.gestion.dao.RoleRepository;
import com.gestionProyecto.gestion.models.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.desktop.PreferencesEvent;
import java.util.List;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private RoleRepository repository;

    @Override
    public List<RoleModel> getAllRoles() {
        return (List<RoleModel>) repository.findAll();
    }

    @Override
    public RoleModel getByIdRole(Long id) {
        return (RoleModel) repository.findById(id).get();
    }

    @Override
    public void save(RoleModel roleModel) {
        repository.save(roleModel);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
