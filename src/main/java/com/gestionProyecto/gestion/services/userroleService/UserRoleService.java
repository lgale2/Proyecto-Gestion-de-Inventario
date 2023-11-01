package com.gestionProyecto.gestion.services.userroleService;

import com.gestionProyecto.gestion.dao.UserRoleRepository;
import com.gestionProyecto.gestion.models.UserRoleModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleService implements IUserRoleService{

    @Autowired
    private UserRoleRepository repository;
    @Override
    public List<UserRoleModel> getAll() {
        return (List<UserRoleModel>) repository.findAll();
    }

    @Override
    public UserRoleModel getById(Long id) {
        return (UserRoleModel) repository.findById(id).get();
    }

    @Override
    public void save(UserRoleModel userRoleModel) {
        repository.save(userRoleModel);
    }

    @Override
    public void update(UserRoleModel userRoleModel) {
        UserRoleModel existingEntity = getById(userRoleModel.getId());

        if(existingEntity != null){
            existingEntity.setIdUser(userRoleModel.getIdUser());
            existingEntity.setIdRole(userRoleModel.getIdRole());
            repository.save(existingEntity);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
