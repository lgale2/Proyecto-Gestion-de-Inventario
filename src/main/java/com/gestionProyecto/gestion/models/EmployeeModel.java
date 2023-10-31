package com.gestionProyecto.gestion.models;

import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name="employees")
@Getter @Setter
@ToString
@EqualsAndHashCode
public class EmployeeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="firstname")
    private String firstname;
    @Column(name="lastname")
    private String lastname;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phone;
    @Column(name="address")
    private String address;
    @Column(name="dateofadmission")
    private String dateofadmission;

   @ManyToOne
    @JoinColumn(name = "position")
   private PositionModel position; // Relación con PositionModel

}
