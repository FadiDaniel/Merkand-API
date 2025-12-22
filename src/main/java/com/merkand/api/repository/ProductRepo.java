package com.merkand.api.repository;

import com.merkand.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;

public interface ProductRepo extends JpaRepository<Product, Long> {
    ArrayList<Product> findByActiveTrue();
    ArrayList<Product> findByNameContainingIgnoreCase(String name);
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.active = false WHERE p.id = ?1")
    void softDeleteById(Long id);
}