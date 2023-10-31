package com.gestionProyecto.gestion.models;

import lombok.*;


import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter @Setter
@ToString @EqualsAndHashCode
public class CategoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_category;
    @Column(name = "category")
    private String category;
}
