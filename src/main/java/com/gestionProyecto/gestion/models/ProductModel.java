package com.gestionProyecto.gestion.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter @Setter
@ToString @EqualsAndHashCode
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long IdProducto;
    @Column(name = "product_name")
    private String producto;
    @Column(name = "description")
    private String descripcion;
    @Column(name = "purchase_price")
    private Double precioCompra;
    @Column(name = "sale_price")
    private Double precioVenta;
    @Column(name = "quantity_stock")
    private Long cantidadStock;
    @ManyToOne
    @JoinColumn(name = "id_category")
    private CategoryModel idCategoria;
    @ManyToOne
    @JoinColumn(name = "id_supplier")
    private SupplierModel idProveedor;

}
