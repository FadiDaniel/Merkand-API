package com.merkand.api.service;

import com.merkand.api.entity.Product;

import java.util.ArrayList;

public interface ProductService {
    Product get(Long productId);
    ArrayList<Product> getAll();
    void save(Product product);
    void delete(Long productId);
    ArrayList<Product> getActiveProducts();
    ArrayList<Product> getByNamePattern(String name);
}
