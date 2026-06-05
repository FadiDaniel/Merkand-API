package com.merkand.api.service.implementation;

import com.merkand.api.entity.Product;
import com.merkand.api.repository.ProductRepo;
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
 * Unit tests for ProductServImpl.
 * Tests the service layer functionality using Mockito for dependency mocking.
 */
@ExtendWith(MockitoExtension.class)
class ProductServImplTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductServImpl productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product(
                1L,
                "Test Product",
                "Test Description",
                "Test Category",
                10.99,
                100,
                10,
                "unit",
                "unit",
                true,
                LocalDate.now(),
                LocalDate.now(),
                null,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    @Test
    void get_ReturnsProduct_WhenProductExists() {
        // Arrange
        when(productRepo.findById(1L)).thenReturn(Optional.of(testProduct));

        // Act
        Product result = productService.get(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Product", result.getName());
        assertEquals(10.99, result.getPrice());
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    void get_ReturnsNull_WhenProductDoesNotExist() {
        // Arrange
        when(productRepo.findById(1L)).thenReturn(Optional.empty());

        // Act
        Product result = productService.get(1L);

        // Assert
        assertNull(result);
        verify(productRepo, times(1)).findById(1L);
    }

    @Test
    void getAll_ReturnsProductList() {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct);
        productList.add(new Product(
                2L,
                "Test Product 2",
                "Test Description 2",
                "Test Category 2",
                15.99,
                50,
                5,
                "unit",
                "unit",
                false,
                LocalDate.now(),
                LocalDate.now(),
                null,
                new ArrayList<>(),
                new ArrayList<>()
        ));
        when(productRepo.findAll()).thenReturn(productList);

        // Act
        ArrayList<Product> result = productService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Product", result.get(0).getName());
        assertEquals("Test Product 2", result.get(1).getName());
        verify(productRepo, times(1)).findAll();
    }

    @Test
    void save_CallsRepositorySave() {
        // Arrange
        when(productRepo.save(testProduct)).thenReturn(testProduct);

        // Act
        productService.save(testProduct);

        // Assert
        verify(productRepo, times(1)).save(testProduct);
    }

    @Test
    void delete_CallsRepositorySoftDelete() {
        // Arrange
        doNothing().when(productRepo).softDeleteById(1L);

        // Act
        productService.delete(1L);

        // Assert
        verify(productRepo, times(1)).softDeleteById(1L);
    }

    @Test
    void getActiveProducts_ReturnsActiveProductList() {
        // Arrange
        ArrayList<Product> activeProducts = new ArrayList<>();
        activeProducts.add(testProduct); // active = true
        when(productRepo.findByActiveTrue()).thenReturn(activeProducts);

        // Act
        ArrayList<Product> result = productService.getActiveProducts();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isActive());
        verify(productRepo, times(1)).findByActiveTrue();
    }

    @Test
    void getByNamePattern_ReturnsMatchingProducts() {
        // Arrange
        ArrayList<Product> matchingProducts = new ArrayList<>();
        matchingProducts.add(testProduct);
        when(productRepo.findByNameContainingIgnoreCase("Test")).thenReturn(matchingProducts);

        // Act
        ArrayList<Product> result = productService.getByNamePattern("Test");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getName());
        verify(productRepo, times(1)).findByNameContainingIgnoreCase("Test");
    }

    @Test
    void getByNamePattern_ReturnsEmptyList_WhenNoMatch() {
        // Arrange
        when(productRepo.findByNameContainingIgnoreCase("Nonexistent")).thenReturn(new ArrayList<>());

        // Act
        ArrayList<Product> result = productService.getByNamePattern("Nonexistent");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(productRepo, times(1)).findByNameContainingIgnoreCase("Nonexistent");
    }
}