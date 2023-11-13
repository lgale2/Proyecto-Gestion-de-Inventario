package com.gestionProyecto.gestion.services.supplierService;

import com.gestionProyecto.gestion.dao.SupplierRepository;
import com.gestionProyecto.gestion.models.SupplierModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements ISupplierService{
    @Autowired
    private SupplierRepository repository;
    @Override
    public List<SupplierModel> getAllSuppliers() {
        return (List<SupplierModel>) repository.findAll();
    }

    @Override
    public SupplierModel getByIdSupplier(Long id) {
        return (SupplierModel) repository.findById(id).get();
    }

    @Override
    public void save(SupplierModel supplierModel) {
        repository.save(supplierModel);
    }

    @Override
    public void update(SupplierModel supplierModel) {
        SupplierModel existingEntity = getByIdSupplier(supplierModel.getIdSupplier());

        if(existingEntity != null){
            existingEntity.setProveedor(supplierModel.getProveedor());
            existingEntity.setTelefono(supplierModel.getTelefono());
            existingEntity.setEmail(supplierModel.getEmail());
            existingEntity.setAddress(supplierModel.getAddress());
            repository.save(existingEntity);
        }
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public boolean exists(Long idSupplier) {
        return repository.existsById(idSupplier);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existByEmail(email);
    }
}
