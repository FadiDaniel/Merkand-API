BEGIN;

-- POBLAR TABLA: users
INSERT INTO public.users (id, active, password, role, username) VALUES
(1, true, '$2a$12$WcY6iLJquantaUiZSOdmwOsm3KofasLhKzM/7ORGsosz1PMoWdmQW', 'ADMIN', 'test1'),
(2, true, '$2a$12$.1KEv/3PlIldCKCYzUAgzu.bPw/lQBlUCqLyw5S0bSoY9NTxYk1TC', 'OPERATOR', 'user1');

-- 1. POBLAR TABLA: suppliers
INSERT INTO public.suppliers (id, active, address, contact_name, email, name, nif, phone) VALUES
(1, true, 'Calle Mayor 10, Madrid', 'Ana Gómez', 'contacto@lacteasur.com', 'Distribuidora Láctea del Sur', 'B12345678', '555-1234'),
(2, true, 'Av. Central 50, Barcelona', 'Roberto Funes', 'ventas@carnepremium.com', 'Carnes Premium S.A.', 'B87654321', '555-5678'),
(3, true, 'Polígono Industrial 3, Valencia', 'Carlos Ruiz', 'pedidos@frescoshnos.com', 'Alimentos Frescos Hnos.', 'B45678901', '555-9012'),
(4, true, 'Ronda Exterior 25, Sevilla', 'Marta Vidal', 'info@bebidasmundiales.com', 'Bebidas Mundiales Ltda.', 'B23456789', '555-3456'),
(5, true, 'Polígono Calle C, Bilbao', 'Elena Soto', 'contacto@limpiezas.es', 'Fitosanitarios y Limpieza S.L.', 'B34567890', '555-7788'),
(6, true, 'Av. del Puerto 15, Cádiz', 'Javier Cano', 'ventas@congeladosrapidos.net', 'Productos Congelados Rápidos', 'B56789012', '555-2020'),
(7, true, 'Polígono Ind. Getafe, Madrid', 'Lucía Méndez', 'pedidos@distgalega.com', 'Distribuidora Galega', 'B99887766', '555-0011'),
(8, true, 'Calle Norte 45, Santander', 'Pedro San Juan', 'info@sumnorte.es', 'Suministros del Norte', 'B55443322', '555-0022');

-- 2. POBLAR TABLA: products
INSERT INTO public.products (id, active, category, created_at, description, minimum_stock, name, price, stock, unit_measure, unit_sale, updated_at, supplier_id) VALUES
(1, true, 'Lácteos', '2024-01-15', 'Leche de vaca fresca y pasteurizada', 50, 'Leche Entera 1L', 1.15, 80, 'Litros', 'Unidad', '2024-12-10', 1),
(2, true, 'Lácteos', '2024-01-15', 'Yogures sin azúcar ni aditivos', 40, 'Yogur Natural Pack x6', 2.5, 120, 'Pack', 'Unidad', '2024-12-10', 1),
(4, true, 'Carnes', '2024-02-01', 'Pollo de corral, 1.5kg aprox.', 30, 'Pollo Entero Fresco', 6.75, 60, 'Kilogramos', 'Unidad', '2024-12-10', 2),
(5, true, 'Frutas y Verduras', '2024-03-10', 'Tomates frescos de temporada', 60, 'Tomate Pera 1kg', 1.8, 150, 'Kilogramos', 'Bolsa', '2024-12-10', 3),
(6, true, 'Frutas y Verduras', '2024-03-10', 'Lechuga fresca, unidad', 80, 'Lechuga Romana', 0.95, 200, 'Unidad', 'Unidad', '2024-12-10', 3),
(7, true, 'Bebidas', '2024-04-05', 'Pack de 6 botellas de agua mineral natural', 100, 'Agua Mineral 1.5L', 3.2, 300, 'Pack', 'Pack', '2024-12-10', 4),
(8, true, 'Bebidas', '2024-04-05', 'Cerveza rubia de sabor suave', 50, 'Cerveza Lager Pack x12', 8.5, 180, 'Pack', 'Pack', '2024-12-10', 4),
(9, true, 'Panadería', '2024-05-20', 'Rebanadas grandes, sin corteza', 40, 'Pan de Molde Blanco', 1.75, 90, 'Unidad', 'Unidad', '2024-12-10', 1),
(10, true, 'Lácteos', '2024-05-20', 'Mantequilla de alta calidad', 30, 'Mantequilla sin Sal 250g', 3.1, 75, 'Gramos', 'Unidad', '2024-12-10', 1),
(11, true, 'Limpieza', '2024-06-11', '1.5L, fórmula concentrada', 50, 'Detergente Ropa Color', 7.45, 110, 'Litros', 'Botella', '2024-12-10', 5),
(12, true, 'Limpieza', '2024-06-11', 'Spray 750ml, aroma pino', 60, 'Desinfectante Multiusos', 2.99, 150, 'Mililitros', 'Botella', '2024-12-10', 5),
(14, true, 'Congelados', '2024-07-01', 'Tarrina de helado cremoso', 35, 'Helado Vainilla 1L', 5.95, 95, 'Litros', 'Tarrina', '2024-12-10', 6),
(15, true, 'Frutas y Verduras', '2024-08-15', '1kg, frescos, primera calidad', 70, 'Plátanos de Canarias', 2.2, 180, 'Kilogramos', 'Bolsa', '2024-12-10', 3),
(16, true, 'Bebidas', '2024-08-15', 'Tostado natural, molido fino', 50, 'Café Molido Natural 250g', 3.85, 130, 'Gramos', 'Paquete', '2024-12-10', 4),
(18, true, 'Carnes', '2024-09-01', 'Salchichas de cerdo, 300g', 40, 'Salchichas Frescas Pack x6', 3.45, 90, 'Pack', 'Pack', '2024-12-10', 2),
(19, true, 'Bebidas', '2024-10-10', 'Bebida carbonatada sin azúcar', 80, 'Refresco Cola Zero 2L', 1.95, 220, 'Litros', 'Botella', '2024-12-10', 4),
(20, true, 'Limpieza', '2024-10-10', 'Pack de 20 bolsas, resistentes', 70, 'Bolsas de Basura 50L', 3.7, 160, 'Pack', 'Pack', '2024-12-10', 5),
(17, true, 'Lácteos', '2024-09-01', 'Queso madurado, sabor intenso', 25, 'Queso Curado 200g', 4.9, 5, 'Gramos', 'Pieza', '2024-12-10', 1),
(3, true, 'Carnes', '2024-02-01', 'Corte magro y tierno, envasado al vacío', 20, 'Filete de Ternera 500g', 8.99, 10, 'Gramos', 'Paquete', '2024-12-10', 2),
(13, true, 'Congelados', '2024-07-01', 'Pizza de masa fina, 350g', 40, 'Pizza Margarita Congelada', 4.1, 20, 'Gramos', 'Unidad', '2024-12-10', 6);

-- 3. POBLAR TABLA: orders
INSERT INTO public.orders (id, observations, order_date, order_number, status, total_amount, supplier_id, user_id) VALUES
(2, 'Carnes de calidad premium', '2024-11-01', 'P-20241101-002', 'RECEIVED', 350.5, 2, 2),
(5, 'Productos de limpieza para el mes', '2024-11-15', 'P-20241115-005', 'RECEIVED', 650.4, 5, 1),
(8, 'Pedido carnes programado para esta semana', '2024-12-12', 'P-20241212-008', 'PENDING', 580, 2, 1),
(6, 'Congelados recibidos en buen estado', '2024-11-20', 'P-20241120-006', 'RECEIVED', 410.25, 6, 2),
(4, 'Productos frescos de temporada', '2024-11-10', 'P-20241110-004', 'RECEIVED', 280.95, 3, 2),
(1, 'Pedido de lácteos recibido correctamente', '2024-10-10', 'P-20241010-001', 'RECEIVED', 185, 1, 2),
(3, 'Reposición bebidas', '2024-11-05', 'P-20241105-003', 'RECEIVED', 490, 4, 2),
(9, 'Cancelado por falta de stock del proveedor', '2024-12-13', 'P-20241213-009', 'CANCELLED', 200, 2, 2),
(7, 'Pedido lácteos pendiente de recepción', '2024-12-05', 'P-20241205-007', 'PENDING', 330, 1, 1),
(10, 'Compra inicio de año', '2025-01-10', 'P-202501-01', 'RECEIVED', 1200.5, 1, 1),
(11, 'Reposición carnes feb', '2025-02-15', 'P-202502-01', 'RECEIVED', 850, 2, 2),
(12, 'Suministros marzo', '2025-03-20', 'P-202503-01', 'RECEIVED', 450.75, 7, 1),
(13, 'Pedido frescos abril', '2025-04-05', 'P-202504-01', 'RECEIVED', 920, 3, 2),
(14, 'Limpieza mayo', '2025-05-12', 'P-202505-01', 'RECEIVED', 310.2, 5, 1),
(15, 'Bebidas verano junio', '2025-06-18', 'P-202506-01', 'RECEIVED', 1540, 4, 2),
(16, 'Congelados julio', '2025-07-22', 'P-202507-01', 'RECEIVED', 670.5, 6, 1),
(17, 'Reposición agosto', '2025-08-05', 'P-202508-01', 'RECEIVED', 890, 8, 2),
(18, 'Frutas sept', '2025-09-10', 'P-202509-01', 'RECEIVED', 420, 3, 1),
(19, 'Lácteos octubre', '2025-10-15', 'P-202510-01', 'RECEIVED', 750.3, 1, 2),
(20, 'Varios noviembre', '2025-11-20', 'P-202511-01', 'RECEIVED', 1100, 7, 1),
(21, 'Cierre de año diciembre', '2025-12-15', 'P-202512-01', 'RECEIVED', 2100, 2, 1);

-- 4. POBLAR TABLA: order_items
INSERT INTO public.order_items (item_id, quantity, sub_total, unit_price, order_id, product_id) VALUES
(11, 60, 360, 6, 5, 11),
(17, 50, 165, 3.3, 7, 17),
(12, 80, 200, 2.5, 5, 12),
(10, 35, 75.95, 2.17, 4, 15),
(18, 40, 400, 10, 8, 3),
(2, 30, 60, 2, 1, 2),
(15, 45, 235.25, 5.22, 6, 14),
(13, 20, 90.4, 4.52, 5, 20),
(5, 14, 95.5, 6.82, 2, 4),
(19, 50, 180, 3.6, 8, 18),
(8, 80, 120, 1.5, 4, 5),
(6, 100, 300, 3, 3, 7),
(16, 150, 165, 1.1, 7, 1),
(4, 30, 255, 8.5, 2, 3),
(1, 100, 100, 1, 1, 1),
(3, 50, 25, 0.5, 1, 10),
(14, 50, 175, 3.5, 6, 13),
(9, 100, 85, 0.85, 4, 6),
(7, 20, 190, 9.5, 3, 8);

-- 5. POBLAR TABLA: stock_movements
INSERT INTO public.stock_movements (id, date, movement_type, quantity, reference, product_id, user_id) VALUES
(11, '2024-11-16 00:00:00', 'IN', 60, 'REC-P-005', 11, 2),
(12, '2024-11-16 00:00:00', 'IN', 80, 'REC-P-005', 12, 2),
(10, '2024-11-11 00:00:00', 'IN', 35, 'REC-P-004', 15, 2),
(2, '2024-10-11 00:00:00', 'IN', 30, 'REC-P-001', 2, 2),
(15, '2024-11-21 00:00:00', 'IN', 45, 'REC-P-006', 14, 2),
(13, '2024-11-16 00:00:00', 'IN', 20, 'REC-P-005', 20, 2),
(5, '2024-11-02 00:00:00', 'IN', 14, 'REC-P-002', 4, 2),
(25, '2025-01-12 10:00:00', 'IN', 100, 'REC-P-202501-01', 1, 1),
(28, '2025-06-20 09:00:00', 'IN', 200, 'REC-P-202506-01', 8, 1),
(29, '2025-06-25 18:00:00', 'OUT', 120, 'VENTA-003', 8, 2),
(30, '2025-08-10 11:00:00', 'OUT', 30, 'VENTA-004', 3, 2),
(31, '2025-10-20 14:00:00', 'OUT', 25, 'VENTA-005', 18, 2),
(32, '2025-11-05 10:00:00', 'ADJUST', -5, 'AJ-ROTURA', 12, 1),
(33, '2025-12-01 10:00:00', 'OUT', 15, 'VENTA-006', 1, 2),
(34, '2025-12-05 12:00:00', 'OUT', 10, 'VENTA-007', 2, 2),
(35, '2025-12-10 09:00:00', 'IN', 50, 'REC-P-202512-01', 3, 1),
(36, '2025-12-18 15:00:00', 'OUT', 20, 'VENTA-008', 3, 2),
(1, '2024-10-25 00:00:00', 'IN', 100, 'REC-P-001', 1, 2),
(38, '2025-05-18 00:00:00', 'OUT', 4, 'VENTA-010', 13, 1),
(39, '2025-07-12 00:00:00', 'OUT', 1, 'VENTA-011', 17, 1),
(41, '2025-04-12 16:15:00', 'OUT', 5, 'V-STAG-005', 10, 2),
(42, '2025-06-05 08:45:00', 'OUT', 12, 'V-STAG-006', 14, 2),
(43, '2025-08-14 13:30:00', 'OUT', 40, 'V-STAG-007', 16, 2),
(44, '2025-09-22 10:10:00', 'OUT', 25, 'V-STAG-008', 20, 2),
(49, '2025-03-15 11:45:00', 'OUT', 20, 'V-STAG-002', 6, 2),
(50, '2025-10-18 00:00:00', 'OUT', 2, 'VENTA-12', 11, 1),
(51, '2025-10-25 00:00:00', 'OUT', 2, 'VENTA-13', 19, 1),
(24, '2025-09-09 00:00:00', 'OUT', 10, 'AJ-ROTURA', 12, 1),
(8, '2025-09-02 00:00:00', 'IN', 80, 'REC-P-004', 5, 2),
(6, '2025-09-05 00:00:00', 'IN', 100, 'REC-P-003', 7, 2),
(16, '2025-10-08 00:00:00', 'OUT', 5, 'AJ-CADUCIDAD', 1, 1),
(4, '2025-10-02 00:00:00', 'IN', 30, 'REC-P-002', 3, 2),
(23, '2025-10-04 00:00:00', 'OUT', 6, 'AJ-MERMA', 15, 2),
(22, '2025-10-05 00:00:00', 'OUT', 3, 'AJ-ROBO', 3, 2),
(3, '2025-07-11 00:00:00', 'IN', 50, 'REC-P-001', 10, 2),
(14, '2025-08-21 00:00:00', 'IN', 50, 'REC-P-006', 13, 2),
(9, '2025-10-21 00:00:00', 'IN', 100, 'REC-P-004', 6, 2),
(7, '2025-09-06 00:00:00', 'IN', 20, 'REC-P-003', 8, 2),
(20, '2025-09-28 16:00:00', 'OUT', 5, 'AJ-CADUCIDAD', 2, 1),
(17, '2025-09-12 16:00:00', 'OUT', 2, 'AJ-PERDIDA', 6, 2),
(18, '2025-09-20 16:00:00', 'OUT', 1, 'AJ-MERMA', 4, 2),
(19, '2025-07-20 16:00:00', 'OUT', 10, 'AJ-INVENTARIO', 5, 1),
(21, '2025-04-20 16:00:00', 'OUT', 20, 'AJ-DONACION', 7, 1),
(26, '2025-07-20 16:00:00', 'OUT', 40, 'VENTA-001', 1, 2),
(27, '2025-06-15 12:00:00', 'OUT', 50, 'VENTA-002', 7, 2),
(37, '2025-05-25 00:00:00', 'OUT', 2, 'VENTA-009', 9, 1),
(40, '2025-10-23 14:20:00', 'OUT', 30, 'V-STAG-004', 9, 2),
(48, '2025-06-10 09:30:00', 'OUT', 15, 'V-STAG-001', 5, 2);

-- Ajuste de secuencias para que los próximos IDs autogenerados sean correctos
SELECT pg_catalog.setval('public.users_id_seq', (SELECT MAX(id) FROM public.users), true);
SELECT pg_catalog.setval('public.order_items_item_id_seq', (SELECT MAX(item_id) FROM public.order_items), true);
SELECT pg_catalog.setval('public.orders_id_seq', (SELECT MAX(id) FROM public.orders), true);
SELECT pg_catalog.setval('public.products_id_seq', (SELECT MAX(id) FROM public.products), true);
SELECT pg_catalog.setval('public.stock_movements_id_seq', (SELECT MAX(id) FROM public.stock_movements), true);
SELECT pg_catalog.setval('public.suppliers_id_seq', (SELECT MAX(id) FROM public.suppliers), true);

COMMIT;