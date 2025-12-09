package com.merkand.api.repository;

import com.merkand.api.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    ArrayList<Supplier> findByActiveTrue();
    ArrayList<Supplier> findByNameContainingIgnoreCase(String name);

}