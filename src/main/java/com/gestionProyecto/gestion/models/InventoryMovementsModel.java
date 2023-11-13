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

   // @Enumerated(EnumType.STRING)
    @Column(name = "movement_type")
    private String movementType;
    @Column(name = "movement_date")
    private String fechaMovimiento;
    @Column(name = "quantity")
    private String quantity;
    @Column(name = "unit_price")
    private String precioUnitario;
    @Column(name = "total")
    private String total;
    @Column(name = "invoice_reference")
    private String preferenciaFactura;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private EmployeeModel id_empleado;

//    public enum MovementType implements CharSequence {
//        E,
//        S// Otras opciones como SALIDA
//    }

}


