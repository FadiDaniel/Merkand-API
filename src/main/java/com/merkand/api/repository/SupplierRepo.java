package com.merkand.api.repository;

import com.merkand.api.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Long> {
    ArrayList<Supplier> findByActiveTrue();
    ArrayList<Supplier> findByNameContainingIgnoreCase(String name);
    @Transactional
    @Modifying
    @Query("UPDATE Supplier s SET s.active = false WHERE s.id = ?1")
    void softDeleteById(Long id);
}