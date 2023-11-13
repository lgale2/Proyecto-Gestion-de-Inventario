package com.gestionProyecto.gestion.controllers;

import com.gestionProyecto.gestion.models.SupplierModel;
import com.gestionProyecto.gestion.services.supplierService.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class SupplierController {
    @Autowired
    private ISupplierService service;

    @GetMapping("/api/suppliersList")
    public List<SupplierModel>getAllSuppliers(){
        return (List<SupplierModel>) service.getAllSuppliers();
    }

    @GetMapping("/api/supplierById/{id}")
    public SupplierModel SuppliergetById(@PathVariable String id){

        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es válido.");
        }

        Long supplierId = Long.parseLong(id);

        if (!service.exists(supplierId)) {
            throw new RuntimeException("No se encontró ningún proveedor con el ID proporcionado.");
        }

        return service.getByIdSupplier(Long.parseLong(id));
    }

    @PostMapping("/api/supplier/save")
    public void save(@RequestBody SupplierModel supplierModel){

        String supplier = supplierModel.getProveedor();
        String phone = supplierModel.getTelefono();
        String email = supplierModel.getEmail();
        String address = supplierModel.getAddress();

        isValidField(supplier, "Proveedor", 3, 40, false, true);
        isValidField(phone, "Telefono",8, 8, false, false);
        isValidField(email, "Correo electronico", 8, 100, false, true);
        isValidField(address, "Direccion", 3, 100, false, true);

        if (!isValidEmail(email)) {
            throw new RuntimeException("El correo electronico no es valido");
        }
        if(service.existsByEmail(email)){
            throw new RuntimeException("El correo electronico ya esta registrado.");
        }

        service.save(supplierModel);
    }

    public void isValidField(String fieldname,String fieldvalue, int minLength, int maxLength, boolean allowEmpty, boolean allowLetters ){
        if (fieldvalue==null||fieldvalue.isEmpty()){
            if (!allowEmpty){
                throw new RuntimeException("No se permite dejar el campo "+ fieldname+" vacio.");
            } else if (fieldvalue.length()<minLength || fieldvalue.length()>maxLength) {
                throw new RuntimeException("La longitud del campo " + fieldname + " debe estar entre " + minLength + " y " + maxLength + " caracteres.");

            }

        }
    }

    private boolean isValidEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return  matcher.matches();
    }


    @PutMapping("/api/supplier/update/{id}")
    public void update(@PathVariable String id, @RequestBody SupplierModel supplierModel){

        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es válido.");
        }

        Long supplierId = Long.parseLong(id);

        if (!service.exists(supplierId)) {
            throw new RuntimeException("No se encontró ningún proveedor con el ID proporcionado.");
        }
        SupplierModel existingSupplier = service.getByIdSupplier(Long.parseLong(id));
        
        if(existingSupplier != null){

            String supplier = supplierModel.getProveedor();
            String phone = supplierModel.getTelefono();
            String email = supplierModel.getEmail();
            String address = supplierModel.getAddress();

            isValidField(supplier, "Proveedor", 3, 40, false, true);
            isValidField(phone, "Telefono",8, 8, false, false);
            isValidField(email, "Correo electronico", 8, 100, false, true);
            isValidField(address, "Direccion", 3, 100, false, true);

            if (!isValidEmail(email)) {
                throw new RuntimeException("El correo electronico no es valido");
            }
            if(service.existsByEmail(email)){
                throw new RuntimeException("El correo electronico ya esta registrado.");
            }

            existingSupplier.setProveedor(supplierModel.getProveedor());
            existingSupplier.setTelefono(supplierModel.getTelefono());
            existingSupplier.setEmail(supplierModel.getEmail());
            existingSupplier.setAddress(supplierModel.getAddress());
            service.update(existingSupplier);
        }
    }

    @DeleteMapping("/api/supplier/delete/{id}")
    public void delete(@PathVariable String id){
        if (id == null || !id.matches("\\d+")) {
            throw new RuntimeException("El ID proporcionado no es válido.");
        }

        Long supplierId = Long.parseLong(id);

        if (!service.exists(supplierId)) {
            throw new RuntimeException("No se encontró ningún proveedor con el ID proporcionado.");
        }

        service.delete(Long.parseLong(id));
    }
}
