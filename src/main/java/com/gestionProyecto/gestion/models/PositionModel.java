package com.gestionProyecto.gestion.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="positions")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PositionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPosition;
    @Column(name="nameposition")
    private String position;


}
