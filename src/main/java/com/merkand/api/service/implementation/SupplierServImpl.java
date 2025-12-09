package com.merkand.api.service.implementation;

import com.merkand.api.entity.Supplier;
import com.merkand.api.repository.SupplierRepo;
import com.merkand.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SupplierServImpl implements SupplierService {
    @Autowired
    private SupplierRepo repo;

    @Override
    public Supplier get(Long supplierId) {
        Optional<Supplier> opt = repo.findById(supplierId);
        return opt.orElse(null);
    }

    @Override
    public ArrayList<Supplier> getAll() {
        return (ArrayList<Supplier>) repo.findAll();
    }

    @Override
    public void save(Supplier supplier) {
        repo.save(supplier);
    }

    @Override
    public void delete(Long supplierId) {
        repo.deleteById(supplierId);
    }

    @Override
    public ArrayList<Supplier> getActiveSuppliers() {
        return repo.findByActiveTrue();
    }

    @Override
    public ArrayList<Supplier> getByNamePattern(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}
