package com.merkand.api.service.implementation;

import com.merkand.api.entity.StockMovement;
import com.merkand.api.repository.StockMovementRepo;
import com.merkand.api.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class StockMovementServImpl implements StockMovementService {
    @Autowired
    private StockMovementRepo repo;

    @Override
    public StockMovement get(Long movementId) {
        Optional<StockMovement> opt = repo.findById(movementId);
        return opt.orElse(null);
    }

    @Override
    public ArrayList<StockMovement> getAll() {
        return (ArrayList<StockMovement>) repo.findAll();
    }

    @Override
    public void save(StockMovement movement) {
        repo.save(movement);
    }

    @Override
    public void delete(Long movementId) {
        repo.deleteById(movementId);
    }

    @Override
    public ArrayList<StockMovement> getByProductId(Long productId) {
        return repo.findByProduct_Id(productId);
    }
}
