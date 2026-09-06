package com.merkand.api.service.implementation;

import com.merkand.api.entity.OrderItem;
import com.merkand.api.entity.Order;
import com.merkand.api.entity.Product;
import com.merkand.api.repository.OrderItemRepo;
import com.merkand.api.service.OrderItemService;
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
 * Unit tests for OrderItemServImpl.
 * Tests the service layer functionality using Mockito for dependency mocking.
 */
@ExtendWith(MockitoExtension.class)
class OrderItemServImplTest {

    @Mock
    private OrderItemRepo orderItemRepo;

    @InjectMocks
    private OrderItemServImpl orderItemService;

    private OrderItem testOrderItem;
    private Order testOrder;
    private Product testProduct;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderNumber("CUSTOMER-001");
        testOrder.setTotalAmount(50.0);
        testOrder.setOrderDate(LocalDate.now());
        testOrder.setStatus(com.merkand.api.entity.enums.Status.PENDING);

        testProduct = new Product();
        testProduct.setId(1L);
        testProduct.setName("Test Product");
        testProduct.setDescription("Test Description");
        testProduct.setCategory("Test Category");
        testProduct.setPrice(10.99);
        testProduct.setStock(100);
        testProduct.setMinimumStock(10);
        testProduct.setUnitSale("unit");
        testProduct.setUnitMeasure("unit");
        testProduct.setActive(true);
        testProduct.setCreatedAt(LocalDate.now());
        testProduct.setUpdatedAt(LocalDate.now());

        testOrderItem = new OrderItem();
        testOrderItem.setItemId(1L);
        testOrderItem.setQuantity(5);
        testOrderItem.setUnitPrice(10.99);
        testOrderItem.setSubTotal(54.95);
        testOrderItem.setProduct(testProduct);
        testOrderItem.setOrder(testOrder);
    }

    @Test
    void get_ReturnsOrderItem_WhenOrderItemExists() {
        // Arrange
        when(orderItemRepo.findById(1L)).thenReturn(Optional.of(testOrderItem));

        // Act
        OrderItem result = orderItemService.get(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getItemId());
        assertEquals(5, result.getQuantity());
        assertEquals(testOrder, result.getOrder());
        assertEquals(testProduct, result.getProduct());
        verify(orderItemRepo, times(1)).findById(1L);
    }

    @Test
    void get_ReturnsNull_WhenOrderItemDoesNotExist() {
        // Arrange
        when(orderItemRepo.findById(1L)).thenReturn(Optional.empty());

        // Act
        OrderItem result = orderItemService.get(1L);

        // Assert
        assertNull(result);
        verify(orderItemRepo, times(1)).findById(1L);
    }

    @Test
    void getAll_ReturnsOrderItemList() {
        // Arrange
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(testOrderItem);
        OrderItem secondItem = new OrderItem();
        secondItem.setItemId(2L);
        secondItem.setQuantity(3);
        secondItem.setUnitPrice(10.99);
        secondItem.setSubTotal(32.97);
        secondItem.setProduct(testProduct);
        secondItem.setOrder(testOrder);
        orderItemList.add(secondItem);
        when(orderItemRepo.findAll()).thenReturn(orderItemList);

        // Act
        ArrayList<OrderItem> result = orderItemService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals(3, result.get(1).getQuantity());
        verify(orderItemRepo, times(1)).findAll();
    }

    @Test
    void save_CallsRepositorySave() {
        // Arrange
        when(orderItemRepo.save(testOrderItem)).thenReturn(testOrderItem);

        // Act
        orderItemService.save(testOrderItem);

        // Assert
        verify(orderItemRepo, times(1)).save(testOrderItem);
    }

    @Test
    void delete_CallsRepositoryDeleteById() {
        // Arrange
        doNothing().when(orderItemRepo).deleteById(1L);

        // Act
        orderItemService.delete(1L);

        // Assert
        verify(orderItemRepo, times(1)).deleteById(1L);
    }

    @Test
    void existsById_ReturnsTrue_WhenOrderItemExists() {
        // Arrange
        when(orderItemRepo.existsById(1L)).thenReturn(true);

        // Act
        boolean result = orderItemService.existsById(1L);

        // Assert
        assertTrue(result);
        verify(orderItemRepo, times(1)).existsById(1L);
    }

    @Test
    void existsById_ReturnsFalse_WhenOrderItemDoesNotExist() {
        // Arrange
        when(orderItemRepo.existsById(1L)).thenReturn(false);

        // Act
        boolean result = orderItemService.existsById(1L);

        // Assert
        assertFalse(result);
        verify(orderItemRepo, times(1)).existsById(1L);
    }

    @Test
    void getByOrderId_ReturnsOrderItemsForOrder() {
        // Arrange
        ArrayList<OrderItem> orderItemsForOrder = new ArrayList<>();
        orderItemsForOrder.add(testOrderItem);
        when(orderItemRepo.findByOrder_Id(1L)).thenReturn(orderItemsForOrder);

        // Act
        ArrayList<OrderItem> result = orderItemService.getByOrderId(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).getQuantity());
        assertEquals(testOrder, result.get(0).getOrder());
        verify(orderItemRepo, times(1)).findByOrder_Id(1L);
    }

    @Test
    void getByOrderId_ReturnsEmptyList_WhenNoOrderItemsForOrder() {
        // Arrange
        when(orderItemRepo.findByOrder_Id(1L)).thenReturn(new ArrayList<>());

        // Act
        ArrayList<OrderItem> result = orderItemService.getByOrderId(1L);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(orderItemRepo, times(1)).findByOrder_Id(1L);
    }
}