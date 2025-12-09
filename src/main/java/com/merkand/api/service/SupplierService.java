package com.merkand.api.service;

import com.merkand.api.entity.Supplier;

import java.util.ArrayList;

public interface SupplierService {
    public Supplier get(Long supplierId);
    public ArrayList<Supplier> getAll();
    public void save(Supplier supplier);
    public void delete(Long supplierId);
    public ArrayList<Supplier> getActiveSuppliers();
    public ArrayList<Supplier> getByNamePattern(String name);
}
