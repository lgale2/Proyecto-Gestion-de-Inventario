package com.gestionProyecto.gestion.models;

import javax.persistence.*;

@Entity
@Table(name="role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_role")
    private Long IdRole;
    @Column(name = "role")
    private String role;


}
