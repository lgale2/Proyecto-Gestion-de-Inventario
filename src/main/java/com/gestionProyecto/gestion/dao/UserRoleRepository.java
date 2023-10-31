package com.gestionProyecto.gestion.dao;

import com.gestionProyecto.gestion.models.UserRoleModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRoleModel, Long> {
}
