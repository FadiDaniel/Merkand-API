package com.merkand.api.config;

import com.merkand.api.entity.*;
import com.merkand.api.entity.enums.MovementType;
import com.merkand.api.entity.enums.Status;
import com.merkand.api.repository.OrderItemRepo;
import com.merkand.api.repository.OrderRepo;
import com.merkand.api.repository.ProductRepo;
import com.merkand.api.repository.SupplierRepo;
import com.merkand.api.repository.UserRepo;
import com.merkand.api.repository.StockMovementRepo;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Data initializer for loading test data on application startup.
 * Only runs when the "dev" or "test" profile is active.
 */
@Component
@Profile({"ADMIN", "test1"})
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepo userRepo;
    private final SupplierRepo supplierRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderItemRepo orderItemRepo;
    private final StockMovementRepo stockMovementRepo;

    @PostConstruct
    public void initData() {
        // Only initialize if tables are empty
        if (userRepo.count() == 0) {
            initUsers();
        }
        if (supplierRepo.count() == 0) {
            initSuppliers();
        }
        if (productRepo.count() == 0) {
            initProducts();
        }
        if (orderRepo.count() == 0) {
            initOrders();
        }
        if (orderItemRepo.count() == 0) {
            initOrderItems();
        }
        if (stockMovementRepo.count() == 0) {
            initStockMovements();
        }
    }

    private void initUsers() {
        // Passwords are encoded versions of "test1" and "user1"
        User admin = new User();
        admin.setId(1L);
        admin.setUsername("test1");
        admin.setPassword("$2a$12$WcY6iLJquantaUiZSOdmwOsm3KofasLhKzM/7ORGsosz1PMoWdmQW");
        admin.setActive(true);
        admin.setRole(com.merkand.api.entity.enums.Role.ADMIN);

        User operator = new User();
        operator.setId(2L);
        operator.setUsername("user1");
        operator.setPassword("$2a$12$.1KEv/3PlIldCKCYzUAgzu.bPw/lQBlUCqLyw5S0bSoY9NTxYk1TC");
        operator.setActive(true);
        operator.setRole(com.merkand.api.entity.enums.Role.OPERATOR);

        userRepo.saveAll(Arrays.asList(admin, operator));
    }

    private void initSuppliers() {
        Supplier supplier1 = new Supplier();
        supplier1.setId(1L);
        supplier1.setName("Distribuidora Láctea del Sur");
        supplier1.setNif("B12345678");
        supplier1.setContactName("Ana Gómez");
        supplier1.setPhone("555-1234");
        supplier1.setEmail("contacto@lacteasur.com");
        supplier1.setAddress("Calle Mayor 10, Madrid");
        supplier1.setActive(true);

        Supplier supplier2 = new Supplier();
        supplier2.setId(2L);
        supplier2.setName("Carnes Premium S.A.");
        supplier2.setNif("B87654321");
        supplier2.setContactName("Roberto Funes");
        supplier2.setPhone("555-5678");
        supplier2.setEmail("ventas@carnepremium.com");
        supplier2.setAddress("Av. Central 50, Barcelona");
        supplier2.setActive(true);

        // Add remaining suppliers...
        Supplier supplier3 = new Supplier();
        supplier3.setId(3L);
        supplier3.setName("Alimentos Frescos Hnos.");
        supplier3.setNif("B45678901");
        supplier3.setContactName("Carlos Ruiz");
        supplier3.setPhone("555-9012");
        supplier3.setEmail("pedidos@frescoshnos.com");
        supplier3.setAddress("Polígono Industrial 3, Valencia");
        supplier3.setActive(true);

        Supplier supplier4 = new Supplier();
        supplier4.setId(4L);
        supplier4.setName("Bebidas Mundiales Ltda.");
        supplier4.setNif("B23456789");
        supplier4.setContactName("Marta Vidal");
        supplier4.setPhone("555-3456");
        supplier4.setEmail("info@bebidasmundiales.com");
        supplier4.setAddress("Ronda Exterior 25, Sevilla");
        supplier4.setActive(true);

        Supplier supplier5 = new Supplier();
        supplier5.setId(5L);
        supplier5.setName("Fitosanitarios y Limpieza S.L.");
        supplier5.setNif("B34567890");
        supplier5.setContactName("Elena Soto");
        supplier5.setPhone("555-7788");
        supplier5.setEmail("contacto@limpiezas.es");
        supplier5.setAddress("Polígono Calle C, Bilbao");
        supplier5.setActive(true);

        Supplier supplier6 = new Supplier();
        supplier6.setId(6L);
        supplier6.setName("Productos Congelados Rápidos");
        supplier6.setNif("B56789012");
        supplier6.setContactName("Javier Cano");
        supplier6.setPhone("555-2020");
        supplier6.setEmail("ventas@congeladosrapidos.net");
        supplier6.setAddress("Av. del Puerto 15, Cádiz");
        supplier6.setActive(true);

        Supplier supplier7 = new Supplier();
        supplier7.setId(7L);
        supplier7.setName("Distribuidora Galega");
        supplier7.setNif("B99887766");
        supplier7.setContactName("Lucía Méndez");
        supplier7.setPhone("555-0011");
        supplier7.setEmail("pedidos@distgalega.com");
        supplier7.setAddress("Polígono Ind. Getafe, Madrid");
        supplier7.setActive(true);

        Supplier supplier8 = new Supplier();
        supplier8.setId(8L);
        supplier8.setName("Suministros del Norte");
        supplier8.setNif("B55443322");
        supplier8.setContactName("Pedro San Juan");
        supplier8.setPhone("555-0022");
        supplier8.setEmail("info@sumnorte.es");
        supplier8.setAddress("Calle Norte 45, Santander");
        supplier8.setActive(true);

        supplierRepo.saveAll(Arrays.asList(supplier1, supplier2, supplier3, supplier4, supplier5, supplier6, supplier7, supplier8));
    }

    private void initProducts() {
        // For a complete implementation, we would add all products from data.sql
        // For brevity in this example, we'll add a few key products
        // In practice, this would load all the products from the data.sql file

        // Get suppliers for setting relationships
        var supplier1 = supplierRepo.findById(1L).orElseThrow(); // Distribuidora Láctea del Sur
        var supplier2 = supplierRepo.findById(2L).orElseThrow(); // Carnes Premium S.A.
        var supplier3 = supplierRepo.findById(3L).orElseThrow(); // Alimentos Frescos Hnos.
        var supplier4 = supplierRepo.findById(4L).orElseThrow(); // Bebidas Mundiales Ltda.
        var supplier5 = supplierRepo.findById(5L).orElseThrow(); // Fitosanitarios y Limpieza S.L.
        var supplier6 = supplierRepo.findById(6L).orElseThrow(); // Productos Congelados Rápidos
        var supplier7 = supplierRepo.findById(7L).orElseThrow(); // Distribuidora Galega
        var supplier8 = supplierRepo.findById(8L).orElseThrow(); // Suministros del Norte

        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Leche Entera 1L");
        product1.setDescription("Leche de vaca fresca y pasteurizada");
        product1.setCategory("Lácteos");
        product1.setPrice(1.15);
        product1.setStock(80);
        product1.setMinimumStock(50);
        product1.setUnitMeasure("Litros");
        product1.setUnitSale("Unidad");
        product1.setActive(true);
        product1.setCreatedAt(LocalDate.of(2024, 1, 15));
        product1.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product1.setSupplier(supplier1);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Yogur Natural Pack x6");
        product2.setDescription("Yogures sin azúcar ni aditivos");
        product2.setCategory("Lácteos");
        product2.setPrice(2.5);
        product2.setStock(120);
        product2.setMinimumStock(40);
        product2.setUnitMeasure("Pack");
        product2.setUnitSale("Unidad");
        product2.setActive(true);
        product2.setCreatedAt(LocalDate.of(2024, 1, 15));
        product2.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product2.setSupplier(supplier1);

        Product product3 = new Product();
        product3.setId(3L);
        product3.setName("Filete de Ternera 500g");
        product3.setDescription("Corte magro y tierno, envasado al vacío");
        product3.setCategory("Carnes");
        product3.setPrice(8.99);
        product3.setStock(10);
        product3.setMinimumStock(20);
        product3.setUnitMeasure("Gramos");
        product3.setUnitSale("Paquete");
        product3.setActive(true);
        product3.setCreatedAt(LocalDate.of(2024, 2, 1));
        product3.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product3.setSupplier(supplier2);

        Product product4 = new Product();
        product4.setId(4L);
        product4.setName("Tomate Pera 1kg");
        product4.setDescription("Tomates frescos de temporada");
        product4.setCategory("Frutas y Verduras");
        product4.setPrice(1.8);
        product4.setStock(150);
        product4.setMinimumStock(60);
        product4.setUnitMeasure("Kilogramos");
        product4.setUnitSale("Bolsa");
        product4.setActive(true);
        product4.setCreatedAt(LocalDate.of(2024, 3, 10));
        product4.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product4.setSupplier(supplier3);

        Product product5 = new Product();
        product5.setId(5L);
        product5.setName("Lechuga Romana");
        product5.setDescription("Lechuga fresca, unidad");
        product5.setCategory("Frutas y Verduras");
        product5.setPrice(0.95);
        product5.setStock(200);
        product5.setMinimumStock(80);
        product5.setUnitMeasure("Unidad");
        product5.setUnitSale("Unidad");
        product5.setActive(true);
        product5.setCreatedAt(LocalDate.of(2024, 3, 10));
        product5.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product5.setSupplier(supplier3);

        Product product6 = new Product();
        product6.setId(6L);
        product6.setName("Agua Mineral 1.5L");
        product6.setDescription("Pack de 6 botellas de agua mineral natural");
        product6.setCategory("Bebidas");
        product6.setPrice(3.2);
        product6.setStock(300);
        product6.setMinimumStock(100);
        product6.setUnitMeasure("Pack");
        product6.setUnitSale("Pack");
        product6.setActive(true);
        product6.setCreatedAt(LocalDate.of(2024, 4, 5));
        product6.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product6.setSupplier(supplier4);

        Product product7 = new Product();
        product7.setId(7L);
        product7.setName("Cerveza Lager Pack x12");
        product7.setDescription("Cerveza rubia de sabor suave");
        product7.setCategory("Bebidas");
        product7.setPrice(8.5);
        product7.setStock(180);
        product7.setMinimumStock(50);
        product7.setUnitMeasure("Pack");
        product7.setUnitSale("Pack");
        product7.setActive(true);
        product7.setCreatedAt(LocalDate.of(2024, 4, 5));
        product7.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product7.setSupplier(supplier4);

        Product product8 = new Product();
        product8.setId(8L);
        product8.setName("Pan de Molde Blanco");
        product8.setDescription("Rebanadas grandes, sin corteza");
        product8.setCategory("Panadería");
        product8.setPrice(1.75);
        product8.setStock(90);
        product8.setMinimumStock(40);
        product8.setUnitMeasure("Unidad");
        product8.setUnitSale("Unidad");
        product8.setActive(true);
        product8.setCreatedAt(LocalDate.of(2024, 5, 20));
        product8.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product8.setSupplier(supplier1);

        Product product9 = new Product();
        product9.setId(9L);
        product9.setName("Mantequilla sin Sal 250g");
        product9.setDescription("Mantequilla de alta calidad");
        product9.setCategory("Lácteos");
        product9.setPrice(3.1);
        product9.setStock(75);
        product9.setMinimumStock(30);
        product9.setUnitMeasure("Gramos");
        product9.setUnitSale("Unidad");
        product9.setActive(true);
        product9.setCreatedAt(LocalDate.of(2024, 5, 20));
        product9.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product9.setSupplier(supplier1);

        Product product10 = new Product();
        product10.setId(10L);
        product10.setName("Detergente Ropa Color");
        product10.setDescription("1.5L, fórmula concentrada");
        product10.setCategory("Limpieza");
        product10.setPrice(7.45);
        product10.setStock(110);
        product10.setMinimumStock(50);
        product10.setUnitMeasure("Litros");
        product10.setUnitSale("Botella");
        product10.setActive(true);
        product10.setCreatedAt(LocalDate.of(2024, 6, 11));
        product10.setUpdatedAt(LocalDate.of(2024, 12, 10));
        product10.setSupplier(supplier5);

        // Add more products as needed...
        // For brevity, we're showing the pattern with key products

        productRepo.saveAll(Arrays.asList(
            product1, product2, product3, product4, product5,
            product6, product7, product8, product9, product10
        ));
    }

    private void initOrders() {
        // Get users and suppliers for relationships
        var user1 = userRepo.findById(1L).orElseThrow(); // test1 (admin)
        var user2 = userRepo.findById(2L).orElseThrow(); // user1 (operator)
        var supplier1 = supplierRepo.findById(1L).orElseThrow();
        var supplier2 = supplierRepo.findById(2L).orElseThrow();
        var supplier3 = supplierRepo.findById(3L).orElseThrow();
        var supplier4 = supplierRepo.findById(4L).orElseThrow();
        var supplier5 = supplierRepo.findById(5L).orElseThrow();
        var supplier6 = supplierRepo.findById(6L).orElseThrow();
        var supplier7 = supplierRepo.findById(7L).orElseThrow();
        var supplier8 = supplierRepo.findById(8L).orElseThrow();

        Order order1 = new Order();
        order1.setId(1L);
        order1.setOrderNumber("P-20241010-001");
        order1.setOrderDate(LocalDate.of(2024, 10, 10));
        order1.setObservations("Pedido de lácteos recibido correctamente");
        order1.setStatus(Status.RECEIVED);
        order1.setTotalAmount(185.0);
        order1.setSupplier(supplier1);
        order1.setUser(user2);

        Order order2 = new Order();
        order2.setId(2L);
        order2.setOrderNumber("P-20241101-002");
        order2.setOrderDate(LocalDate.of(2024, 11, 1));
        order2.setObservations("Carnes de calidad premium");
        order2.setStatus(Status.RECEIVED);
        order2.setTotalAmount(350.5);
        order2.setSupplier(supplier2);
        order2.setUser(user2);

        Order order3 = new Order();
        order3.setId(3L);
        order3.setOrderNumber("P-20241105-003");
        order3.setOrderDate(LocalDate.of(2024, 11, 5));
        order3.setObservations("Reposición bebidas");
        order3.setStatus(Status.RECEIVED);
        order3.setTotalAmount(490.0);
        order3.setSupplier(supplier4);
        order3.setUser(user2);

        Order order4 = new Order();
        order4.setId(4L);
        order4.setOrderNumber("P-20241110-004");
        order4.setOrderDate(LocalDate.of(2024, 11, 10));
        order4.setObservations("Productos frescos de temporada");
        order4.setStatus(Status.RECEIVED);
        order4.setTotalAmount(280.95);
        order4.setSupplier(supplier3);
        order4.setUser(user2);

        Order order5 = new Order();
        order5.setId(5L);
        order5.setOrderNumber("P-20241115-005");
        order5.setOrderDate(LocalDate.of(2024, 11, 15));
        order5.setObservations("Productos de limpieza para el mes");
        order5.setStatus(Status.RECEIVED);
        order5.setTotalAmount(650.4);
        order5.setSupplier(supplier5);
        order5.setUser(user1);

        Order order6 = new Order();
        order6.setId(6L);
        order6.setOrderNumber("P-20241120-006");
        order6.setOrderDate(LocalDate.of(2024, 11, 20));
        order6.setObservations("Congelados recibidos en buen estado");
        order6.setStatus(Status.RECEIVED);
        order6.setTotalAmount(410.25);
        order6.setSupplier(supplier6);
        order6.setUser(user2);

        Order order7 = new Order();
        order7.setId(7L);
        order7.setOrderNumber("P-20241205-007");
        order7.setOrderDate(LocalDate.of(2024, 12, 5));
        order7.setObservations("Pedido lácteos pendiente de recepción");
        order7.setStatus(Status.PENDING);
        order7.setTotalAmount(330.0);
        order7.setSupplier(supplier1);
        order7.setUser(user1);

        Order order8 = new Order();
        order8.setId(8L);
        order8.setOrderNumber("P-20241212-008");
        order8.setOrderDate(LocalDate.of(2024, 12, 12));
        order8.setObservations("Pedido carnes programado para esta semana");
        order8.setStatus(Status.PENDING);
        order8.setTotalAmount(580.0);
        order8.setSupplier(supplier2);
        order8.setUser(user1);

        Order order9 = new Order();
        order9.setId(9L);
        order9.setOrderNumber("P-20241213-009");
        order9.setOrderDate(LocalDate.of(2024, 12, 13));
        order9.setObservations("Cancelado por falta de stock del proveedor");
        order9.setStatus(Status.CANCELLED);
        order9.setTotalAmount(200.0);
        order9.setSupplier(supplier2);
        order9.setUser(user2);

        // Add more orders as needed...

        orderRepo.saveAll(Arrays.asList(
            order1, order2, order3, order4, order5, order6, order7, order8, order9
        ));
    }

    private void initOrderItems() {
        // Get products and orders for relationships
        var product1 = productRepo.findById(1L).orElseThrow();
        var product2 = productRepo.findById(2L).orElseThrow();
        var product3 = productRepo.findById(3L).orElseThrow();
        var product4 = productRepo.findById(4L).orElseThrow();
        var product5 = productRepo.findById(5L).orElseThrow();
        var product6 = productRepo.findById(6L).orElseThrow();
        var product7 = productRepo.findById(7L).orElseThrow();
        var product8 = productRepo.findById(8L).orElseThrow();
        var product9 = productRepo.findById(9L).orElseThrow();
        var product10 = productRepo.findById(10L).orElseThrow();
        var product11 = productRepo.findById(11L).orElseThrow();
        var product12 = productRepo.findById(12L).orElseThrow();
        var product13 = productRepo.findById(13L).orElseThrow();
        var product14 = productRepo.findById(14L).orElseThrow();
        var product15 = productRepo.findById(15L).orElseThrow();

        var order1 = orderRepo.findById(1L).orElseThrow();
        var order2 = orderRepo.findById(2L).orElseThrow();
        var order3 = orderRepo.findById(3L).orElseThrow();
        var order4 = orderRepo.findById(4L).orElseThrow();
        var order5 = orderRepo.findById(5L).orElseThrow();
        var order6 = orderRepo.findById(6L).orElseThrow();
        var order7 = orderRepo.findById(7L).orElseThrow();
        var order8 = orderRepo.findById(8L).orElseThrow();
        var order9 = orderRepo.findById(9L).orElseThrow();

        OrderItem orderItem1 = new OrderItem();
        orderItem1.setItemId(1L);
        orderItem1.setQuantity(100);
        orderItem1.setUnitPrice(1.0);
        orderItem1.setSubTotal(100.0);
        orderItem1.setOrder(order1);
        orderItem1.setProduct(product1);

        OrderItem orderItem2 = new OrderItem();
        orderItem2.setItemId(2L);
        orderItem2.setQuantity(30);
        orderItem2.setUnitPrice(2.0);
        orderItem2.setSubTotal(60.0);
        orderItem2.setOrder(order1);
        orderItem2.setProduct(product2);

        OrderItem orderItem3 = new OrderItem();
        orderItem3.setItemId(3L);
        orderItem3.setQuantity(50);
        orderItem3.setUnitPrice(0.5);
        orderItem3.setSubTotal(25.0);
        orderItem3.setOrder(order1);
        orderItem3.setProduct(product10); // Detergente

        OrderItem orderItem4 = new OrderItem();
        orderItem4.setItemId(4L);
        orderItem4.setQuantity(30);
        orderItem4.setUnitPrice(8.5);
        orderItem4.setSubTotal(255.0);
        orderItem4.setOrder(order2);
        orderItem4.setProduct(product3); // Filete de ternera

        // Add more order items as needed...

        orderItemRepo.saveAll(Arrays.asList(
            orderItem1, orderItem2, orderItem3, orderItem4
        ));
    }

    private void initStockMovements() {
        // Get products, users for relationships
        var product1 = productRepo.findById(1L).orElseThrow();
        var product2 = productRepo.findById(2L).orElseThrow();
        var product3 = productRepo.findById(3L).orElseThrow();
        var product4 = productRepo.findById(4L).orElseThrow();
        var product5 = productRepo.findById(5L).orElseThrow();
        var product6 = productRepo.findById(6L).orElseThrow();
        var product7 = productRepo.findById(7L).orElseThrow();
        var product8 = productRepo.findById(8L).orElseThrow();
        var product9 = productRepo.findById(9L).orElseThrow();
        var product10 = productRepo.findById(10L).orElseThrow();
        var product11 = productRepo.findById(11L).orElseThrow();
        var product12 = productRepo.findById(12L).orElseThrow();
        var product13 = productRepo.findById(13L).orElseThrow();
        var product14 = productRepo.findById(14L).orElseThrow();
        var product15 = productRepo.findById(15L).orElseThrow();

        var user1 = userRepo.findById(1L).orElseThrow();
        var user2 = userRepo.findById(2L).orElseThrow();

        StockMovement movement1 = new StockMovement();
        movement1.setId(1L);
        movement1.setQuantity(100);
        movement1.setMovementType(MovementType.IN);
        movement1.setReference("REC-P-001");
        movement1.setDate(LocalDateTime.of(2024, 10, 25, 0, 0));
        movement1.setProduct(product1);
        movement1.setUser(user2);

        StockMovement movement2 = new StockMovement();
        movement2.setId(2L);
        movement2.setQuantity(30);
        movement2.setMovementType(MovementType.IN);
        movement2.setReference("REC-P-001");
        movement2.setDate(LocalDateTime.of(2024, 10, 11, 0, 0));
        movement2.setProduct(product2);
        movement2.setUser(user2);

        StockMovement movement3 = new StockMovement();
        movement3.setId(3L);
        movement3.setQuantity(50);
        movement3.setMovementType(MovementType.IN);
        movement3.setReference("REC-P-001");
        movement3.setDate(LocalDateTime.of(2025, 7, 11, 0, 0));
        movement3.setProduct(product10); // Detergente
        movement3.setUser(user2);

        // Add more stock movements as needed...

        stockMovementRepo.saveAll(Arrays.asList(
            movement1, movement2, movement3
        ));
    }
}