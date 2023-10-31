package com.gestionProyecto.gestion.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="inventorymovements")
@Getter @Setter
@ToString @EqualsAndHashCode
public class InventoryMovementsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement")
    private Long id_movimiento;
    @Column(name = "id_product")
    private Long idProducto;
    @Column(name = "movement_type")
    private String TipoMovimiento;
    @Column(name = "quantity")
    private Long Cantidad;
    @Column(name = "unit_price")
    private Double precioUnitario;
    @Column(name = "total")
    private Double total;
    @Column(name = "invoice_preference")
    private String preferenciaFactura;
    @Column(name = "id_employee")
    private Long id_empleado;

}
