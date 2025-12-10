package com.merkand.api.service;

import com.merkand.api.entity.Supplier;

import java.util.ArrayList;

public interface SupplierService extends CurdService<Supplier> {

    public ArrayList<Supplier> getActiveSuppliers();
    public ArrayList<Supplier> getByNamePattern(String name);
}
