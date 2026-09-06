package com.merkand.api.service.implementation;

import com.merkand.api.entity.Order;
import com.merkand.api.entity.enums.Status;
import com.merkand.api.repository.OrderRepo;
import com.merkand.api.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for OrderServImpl.
 * Tests the service layer functionality using Mockito for dependency mocking.
 */
@ExtendWith(MockitoExtension.class)
class OrderServImplTest {

    @Mock
    private OrderRepo orderRepo;

    @InjectMocks
    private OrderServImpl orderService;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderNumber("CUSTOMER-001");
        testOrder.setStatus(Status.PENDING);
        testOrder.setOrderDate(LocalDate.now());
        testOrder.setTotalAmount(100.0);
    }

    @Test
    void get_ReturnsOrder_WhenOrderExists() {
        // Arrange
        when(orderRepo.findById(1L)).thenReturn(Optional.of(testOrder));

        // Act
        Order result = orderService.get(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("CUSTOMER-001", result.getOrderNumber());
        assertEquals(Status.PENDING, result.getStatus());
        verify(orderRepo, times(1)).findById(1L);
    }

    @Test
    void get_ReturnsNull_WhenOrderDoesNotExist() {
        // Arrange
        when(orderRepo.findById(1L)).thenReturn(Optional.empty());

        // Act
        Order result = orderService.get(1L);

        // Assert
        assertNull(result);
        verify(orderRepo, times(1)).findById(1L);
    }

    @Test
    void getAll_ReturnsOrderList() {
        // Arrange
        List<Order> orderList = new ArrayList<>();
        orderList.add(testOrder);
        orderList.add(new Order());
        orderList.get(1).setId(2L);
        orderList.get(1).setOrderNumber("CUSTOMER-002");
        orderList.get(1).setStatus(Status.RECEIVED);
        orderList.get(1).setOrderDate(LocalDate.now());
        orderList.get(1).setObservations("Test order 2");
        when(orderRepo.findAll()).thenReturn(orderList);

        // Act
        ArrayList<Order> result = orderService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("CUSTOMER-001", result.get(0).getOrderNumber());
        assertEquals("CUSTOMER-002", result.get(1).getOrderNumber());
        verify(orderRepo, times(1)).findAll();
    }

    @Test
    void save_CallsRepositorySave() {
        // Arrange
        when(orderRepo.save(testOrder)).thenReturn(testOrder);

        // Act
        orderService.save(testOrder);

        // Assert
        verify(orderRepo, times(1)).save(testOrder);
    }

    @Test
    void delete_CallsRepositorySoftDelete() {
        // Arrange
        doNothing().when(orderRepo).softDeleteById(1L);

        // Act
        orderService.delete(1L);

        // Assert
        verify(orderRepo, times(1)).softDeleteById(1L);
    }

    @Test
    void existsById_ReturnsTrue_WhenOrderExists() {
        // Arrange
        when(orderRepo.existsById(1L)).thenReturn(true);

        // Act
        boolean result = orderService.existsById(1L);

        // Assert
        assertTrue(result);
        verify(orderRepo, times(1)).existsById(1L);
    }

    @Test
    void existsById_ReturnsFalse_WhenOrderDoesNotExist() {
        // Arrange
        when(orderRepo.existsById(1L)).thenReturn(false);

        // Act
        boolean result = orderService.existsById(1L);

        // Assert
        assertFalse(result);
        verify(orderRepo, times(1)).existsById(1L);
    }

    @Test
    void getByStatus_ReturnsOrdersWithMatchingStatus() {
        // Arrange
        ArrayList<Order> pendingOrders = new ArrayList<>();
        pendingOrders.add(testOrder); // status = PENDING
        when(orderRepo.findByStatus(Status.PENDING)).thenReturn(pendingOrders);

        // Act
        ArrayList<Order> result = orderService.getByStatus(Status.PENDING);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Status.PENDING, result.get(0).getStatus());
        verify(orderRepo, times(1)).findByStatus(Status.PENDING);
    }

    @Test
    void getByStatus_ReturnsEmptyList_WhenNoOrdersMatch() {
        // Arrange
        when(orderRepo.findByStatus(Status.RECEIVED)).thenReturn(new ArrayList<>());

        // Act
        ArrayList<Order> result = orderService.getByStatus(Status.RECEIVED);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderRepo, times(1)).findByStatus(Status.RECEIVED);
    }
}