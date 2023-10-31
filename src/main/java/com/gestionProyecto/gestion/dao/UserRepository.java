package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
}
