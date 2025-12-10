package com.merkand.api.service;

import com.merkand.api.entity.Product;

import java.util.ArrayList;

public interface ProductService extends CurdService<Product> {

    ArrayList<Product> getActiveProducts();
    ArrayList<Product> getByNamePattern(String name);
}
