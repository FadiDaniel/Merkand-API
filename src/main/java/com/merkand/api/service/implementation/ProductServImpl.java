package com.merkand.api.service.implementation;

import com.merkand.api.entity.Product;
import com.merkand.api.repository.ProductRepo;
import com.merkand.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductServImpl implements ProductService {
    @Autowired
    private ProductRepo repo;

    @Override
    public Product get(Long productId) {
        Optional<Product> opt = repo.findById(productId);
        return opt.orElse(null);
    }

    @Override
    public ArrayList<Product> getAll() {
        return (ArrayList<Product>) repo.findAll();
    }

    @Override
    public void save(Product product) {
        repo.save(product);
    }

    public void delete(Long productId) {
        repo.softDeleteById(productId);
    }

    @Override
    public ArrayList<Product> getActiveProducts() {
        return repo.findByActiveTrue();
    }

    @Override
    public ArrayList<Product> getByNamePattern(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
}
