package com.gestionProyecto.gestion.services.supplierService;

import com.gestionProyecto.gestion.models.SupplierModel;

import java.util.List;

public interface ISupplierService {
    List<SupplierModel> getAllSuppliers();

    SupplierModel getByIdSupplier(Long id);

    void save(SupplierModel supplierModel);

    void update(SupplierModel supplierModel);
    void delete(Long id);

    boolean exists(Long idSupplier);
}
