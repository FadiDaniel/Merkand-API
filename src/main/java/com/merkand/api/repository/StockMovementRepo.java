package com.merkand.api.repository;

import com.merkand.api.entity.StockMovement;
import com.merkand.api.entity.enums.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface StockMovementRepo extends JpaRepository<StockMovement, Long> {
    ArrayList<StockMovement> findByProduct_Id(Long productId);
    ArrayList<StockMovement> findByMovementType(MovementType movementType);
}