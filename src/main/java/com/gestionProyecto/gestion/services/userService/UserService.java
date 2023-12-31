package com.gestionProyecto.gestion.services.userService;

import com.gestionProyecto.gestion.dao.UserRepository;
import com.gestionProyecto.gestion.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository repository;

    @Override
    public List<UserModel> getAll() {
        return (List<UserModel>) repository.findAll();
    }

    @Override
    public UserModel getByIdUser(Long id) {
        return (UserModel) repository.findById(id).get();
    }

    @Override
    public void save(UserModel userModel) {
        repository.save(userModel);
    }

    @Override
    public void update(UserModel userModel) {
        UserModel existingEntity = getByIdUser(userModel.getId());
        if(existingEntity != null){
            existingEntity.setUser(userModel.getUser());
            existingEntity.setEmail(userModel.getEmail());
            existingEntity.setPassword(userModel.getPassword());
            existingEntity.setIdEmployee(userModel.getIdEmployee());
            repository.save(existingEntity);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
