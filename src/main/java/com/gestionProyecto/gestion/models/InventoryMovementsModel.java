package com.gestionProyecto.gestion.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="inventorymovements")
@Getter @Setter
@ToString @EqualsAndHashCode
public class InventoryMovementsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement")
    private Long id_movimiento;

    @ManyToOne
    @JoinColumn(name = "id_product")
    private ProductModel idProducto;

    @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private MovementType movementType;
    @Column(name = "movement_date")
    private String fechaMovimiento;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    private Double precioUnitario;
    @Column(name = "total")
    private Double total;
    @Column(name = "invoice_preference")
    private String preferenciaFactura;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private EmployeeModel id_empleado;

    public enum MovementType {
        E,
        S// Otras opciones como SALIDA
    }

}


