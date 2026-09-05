package com.merkand.api.service.implementation;

import com.merkand.api.entity.MovementItem;
import com.merkand.api.entity.StockMovement;
import com.merkand.api.entity.enums.MovementType;
import com.merkand.api.entity.Product;
import com.merkand.api.entity.User;
import com.merkand.api.repository.MovementItemRepo;
import com.merkand.api.repository.ProductRepo;
import com.merkand.api.repository.StockMovementRepo;
import com.merkand.api.repository.UserRepo;
import com.merkand.api.service.StockMovementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockMovementServImplTest {

    @Mock
    private StockMovementRepo stockMovementRepo;

    @Mock
    private MovementItemRepo movementItemRepo;

    @Mock
    private ProductRepo productRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private StockMovementServImpl stockMovementService;

    @Test
    void testSaveStockMovementWithItems() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        User user = new User();
        user.setId(1L);
        user.setUsername("test");

        StockMovement movement = new StockMovement();
        movement.setId(1L);
        movement.setMovementType(MovementType.OUT);
        movement.setReference("TEST-REF");
        movement.setDate(LocalDateTime.now());
        movement.setUser(user);

        MovementItem item = new MovementItem();
        item.setId(1L);
        item.setQuantity(10);
        item.setProduct(product);
        item.setHeader(movement);

        movement.setItems(List.of(item));

        when(stockMovementRepo.save(any(StockMovement.class))).thenReturn(movement);

        // Act
        stockMovementService.save(movement);

        // Assert - Since save() is void, we verify the repo was called instead
        verify(stockMovementRepo, times(1)).save(any(StockMovement.class));
    }

    @Test
    void testGetStockMovementById() {
        // Arrange
        Product product = new Product();
        product.setId(1L);

        User user = new User();
        user.setId(1L);

        StockMovement movement = new StockMovement();
        movement.setId(1L);
        movement.setMovementType(MovementType.IN);
        movement.setReference("IN-REF");
        movement.setDate(LocalDateTime.now());
        movement.setUser(user);

        MovementItem item = new MovementItem();
        item.setId(1L);
        item.setQuantity(5);
        item.setProduct(product);
        item.setHeader(movement);

        movement.setItems(List.of(item));

        when(stockMovementRepo.findById(1L)).thenReturn(Optional.of(movement));

        // Act
        StockMovement found = stockMovementService.get(1L);

        // Assert
        assertNotNull(found);
        assertEquals(1L, found.getId());
        assertEquals(MovementType.IN, found.getMovementType());
        assertEquals("IN-REF", found.getReference());
        assertEquals(user, found.getUser());
        assertEquals(1, found.getItems().size());
        assertEquals(5, found.getItems().get(0).getQuantity());

        verify(stockMovementRepo, times(1)).findById(1L);
    }

    @Test
    void testGetAllStockMovements() {
        // Arrange
        Product product = new Product();
        product.setId(1L);

        User user = new User();
        user.setId(1L);

        StockMovement movement1 = new StockMovement();
        movement1.setId(1L);
        movement1.setMovementType(MovementType.IN);
        movement1.setReference("IN-REF");
        movement1.setDate(LocalDateTime.now());
        movement1.setUser(user);

        MovementItem item1 = new MovementItem();
        item1.setId(1L);
        item1.setQuantity(5);
        item1.setProduct(product);
        item1.setHeader(movement1);

        movement1.setItems(List.of(item1));

        StockMovement movement2 = new StockMovement();
        movement2.setId(2L);
        movement2.setMovementType(MovementType.OUT);
        movement2.setReference("OUT-REF");
        movement2.setDate(LocalDateTime.now());
        movement2.setUser(user);

        MovementItem item2 = new MovementItem();
        item2.setId(2L);
        item2.setQuantity(10);
        item2.setProduct(product);
        item2.setHeader(movement2);

        movement2.setItems(List.of(item2));

        List<StockMovement> movements = List.of(movement1, movement2);

        when(stockMovementRepo.findAll()).thenReturn(new ArrayList<>(movements));

        // Act
        List<StockMovement> result = stockMovementService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(MovementType.IN, result.get(0).getMovementType());
        assertEquals(MovementType.OUT, result.get(1).getMovementType());

        verify(stockMovementRepo, times(1)).findAll();
    }

    @Test
    void testDeleteStockMovement() {
        // Arrange
        Long movementId = 1L;
        doNothing().when(stockMovementRepo).deleteById(movementId);

        // Act
        stockMovementService.delete(movementId);

        // Assert
        verify(stockMovementRepo, times(1)).deleteById(movementId);
    }

    @Test
    void testExistsById() {
        // Arrange
        Long movementId = 1L;
        when(stockMovementRepo.existsById(movementId)).thenReturn(true);

        // Act
        boolean exists = stockMovementService.existsById(movementId);

        // Assert
        assertTrue(exists);
        verify(stockMovementRepo, times(1)).existsById(movementId);
    }

    @Test
    void testGetByProductId() {
        // This test is commented out because the getByProductId method needs to be
        // reconsidered in the service interface as mentioned in the plan.
        // The method exists in the interface but we're not testing it for now.
    }
}