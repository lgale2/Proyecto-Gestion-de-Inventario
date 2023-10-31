package com.gestionProyecto.gestion.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Getter @Setter
@ToString @EqualsAndHashCode
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    Long id;
    @Column(name = "user")
    String user;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @ManyToOne
    @JoinColumn(name = "id_employee")
    private EmployeeModel idEmployee;

}
