package com.gestionProyecto.gestion.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.catalina.User;

import javax.persistence.*;

@Entity
@Table
@Getter @Setter
@ToString @EqualsAndHashCode
public class UserRoleModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_assignment")
    Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel idUser;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private RoleModel idRole;
}
