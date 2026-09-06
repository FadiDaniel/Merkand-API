package com.merkand.api.service.implementation;

import com.merkand.api.entity.Supplier;
import com.merkand.api.repository.SupplierRepo;
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
 * Unit tests for SupplierServImpl.
 * Tests the service layer functionality using Mockito for dependency mocking.
 */
@ExtendWith(MockitoExtension.class)
class SupplierServImplTest {

    @Mock
    private SupplierRepo supplierRepo;

    @InjectMocks
    private SupplierServImpl supplierService;

    private Supplier testSupplier;

    @BeforeEach
    void setUp() {
        testSupplier = new Supplier(
                1L,
                "123456789",
                "Test Supplier",
                "Test Contact",
                "123456789",
                "test@example.com",
                "Test Address",
                true,
                new ArrayList<>(),
                new ArrayList<>()
        );
    }

    @Test
    void get_ReturnsSupplier_WhenSupplierExists() {
        // Arrange
        when(supplierRepo.findById(1L)).thenReturn(Optional.of(testSupplier));

        // Act
        Supplier result = supplierService.get(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test Supplier", result.getName());
        assertEquals("test@example.com", result.getEmail());
        verify(supplierRepo, times(1)).findById(1L);
    }

    @Test
    void get_ReturnsNull_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierRepo.findById(1L)).thenReturn(Optional.empty());

        // Act
        Supplier result = supplierService.get(1L);

        // Assert
        assertNull(result);
        verify(supplierRepo, times(1)).findById(1L);
    }

    @Test
    void getAll_ReturnsSupplierList() {
        // Arrange
        List<Supplier> supplierList = new ArrayList<>();
        supplierList.add(testSupplier);
        supplierList.add(new Supplier(
                2L,
                "987654321",
                "Test Supplier 2",
                "Test Contact 2",
                "987654321",
                "test2@example.com",
                "Test Address 2",
                false,
                new ArrayList<>(),
                new ArrayList<>()
        ));
        when(supplierRepo.findAll()).thenReturn(supplierList);

        // Act
        ArrayList<Supplier> result = supplierService.getAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Supplier", result.get(0).getName());
        assertEquals("Test Supplier 2", result.get(1).getName());
        verify(supplierRepo, times(1)).findAll();
    }

    @Test
    void save_CallsRepositorySave() {
        // Arrange
        when(supplierRepo.save(testSupplier)).thenReturn(testSupplier);

        // Act
        supplierService.save(testSupplier);

        // Assert
        verify(supplierRepo, times(1)).save(testSupplier);
    }

    @Test
    void delete_CallsRepositorySoftDelete() {
        // Arrange
        doNothing().when(supplierRepo).softDeleteById(1L);

        // Act
        supplierService.delete(1L);

        // Assert
        verify(supplierRepo, times(1)).softDeleteById(1L);
    }

    @Test
    void existsById_ReturnsTrue_WhenSupplierExists() {
        // Arrange
        when(supplierRepo.existsById(1L)).thenReturn(true);

        // Act
        boolean result = supplierService.existsById(1L);

        // Assert
        assertTrue(result);
        verify(supplierRepo, times(1)).existsById(1L);
    }

    @Test
    void existsById_ReturnsFalse_WhenSupplierDoesNotExist() {
        // Arrange
        when(supplierRepo.existsById(1L)).thenReturn(false);

        // Act
        boolean result = supplierService.existsById(1L);

        // Assert
        assertFalse(result);
        verify(supplierRepo, times(1)).existsById(1L);
    }

    @Test
    void getActiveSuppliers_ReturnsActiveSupplierList() {
        // Arrange
        ArrayList<Supplier> activeSuppliers = new ArrayList<>();
        activeSuppliers.add(testSupplier); // active = true
        when(supplierRepo.findByActiveTrue()).thenReturn(activeSuppliers);

        // Act
        ArrayList<Supplier> result = supplierService.getActiveSuppliers();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isActive());
        verify(supplierRepo, times(1)).findByActiveTrue();
    }

    @Test
    void getByNamePattern_ReturnsMatchingSuppliers() {
        // Arrange
        ArrayList<Supplier> matchingSuppliers = new ArrayList<>();
        matchingSuppliers.add(testSupplier);
        when(supplierRepo.findByNameContainingIgnoreCase("Test")).thenReturn(matchingSuppliers);

        // Act
        ArrayList<Supplier> result = supplierService.getByNamePattern("Test");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Supplier", result.get(0).getName());
        verify(supplierRepo, times(1)).findByNameContainingIgnoreCase("Test");
    }

    @Test
    void getByNamePattern_ReturnsEmptyList_WhenNoMatch() {
        // Arrange
        when(supplierRepo.findByNameContainingIgnoreCase("Nonexistent")).thenReturn(new ArrayList<>());

        // Act
        ArrayList<Supplier> result = supplierService.getByNamePattern("Nonexistent");

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(supplierRepo, times(1)).findByNameContainingIgnoreCase("Nonexistent");
    }
}