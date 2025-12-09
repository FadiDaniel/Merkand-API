package com.merkand.api.repository;

import com.merkand.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ProductRepo extends JpaRepository<Product, Long> {
    ArrayList<Product> findByActiveTrue();
    ArrayList<Product> findByNameContainingIgnoreCase(String name);
}