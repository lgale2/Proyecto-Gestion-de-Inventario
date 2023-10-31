package com.gestionProyecto.gestion.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "suppliers")
@Getter @Setter
@ToString @EqualsAndHashCode
public class SupplierModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supplier")
    Long IdSupplier;
    @Column(name = "name_supplier")
    String proveedor;
    @Column(name = "phone")
    String telefono;
    @Column(name = "email")
    String email;
    @Column(name = "address")
    String address;

}
