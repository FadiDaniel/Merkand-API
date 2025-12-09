package com.merkand.api.service;

import com.merkand.api.entity.StockMovement;

import java.util.ArrayList;

public interface StockMovementService {
    StockMovement get(Long movementId);
    ArrayList<StockMovement> getAll();
    void save(StockMovement movement);
    void delete(Long movementId);
    ArrayList<StockMovement> getByProductId(Long productId);
}
