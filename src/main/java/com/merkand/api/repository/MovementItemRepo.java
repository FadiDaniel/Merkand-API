package com.merkand.api.repository;

import com.merkand.api.entity.MovementItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface MovementItemRepo extends JpaRepository<MovementItem, Long> {
    // Add any custom query methods if needed
    ArrayList<MovementItem> findByHeader_Id(Long headerId);
}