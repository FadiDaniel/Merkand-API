BEGIN;

-- ============================================
-- TABLA: SUPPLIERS (Proveedores)
-- ============================================
INSERT INTO public.suppliers (id, active, name, contact_name, phone, email, address, nif)
SELECT * FROM (VALUES
                   (1, TRUE, 'Distribuidora Láctea del Sur', 'Ana Gómez', '555-1234', 'contacto@lacteasur.com', 'Calle Mayor 10, Madrid', 'B12345678'),
                   (2, TRUE, 'Carnes Premium S.A.', 'Roberto Funes', '555-5678', 'ventas@carnepremium.com', 'Av. Central 50, Barcelona', 'B87654321'),
                   (3, TRUE, 'Alimentos Frescos Hnos.', 'Carlos Ruiz', '555-9012', 'pedidos@frescoshnos.com', 'Polígono Industrial 3, Valencia', 'B45678901'),
                   (4, TRUE, 'Bebidas Mundiales Ltda.', 'Marta Vidal', '555-3456', 'info@bebidasmundiales.com', 'Ronda Exterior 25, Sevilla', 'B23456789'),
                   (5, TRUE, 'Fitosanitarios y Limpieza S.L.', 'Elena Soto', '555-7788', 'contacto@limpiezas.es', 'Polígono Calle C, Bilbao', 'B34567890'),
                   (6, TRUE, 'Productos Congelados Rápidos', 'Javier Cano', '555-2020', 'ventas@congeladosrapidos.net', 'Av. del Puerto 15, Cádiz', 'B56789012')
              ) AS new_suppliers(id, active, name, contact_name, phone, email, address, nif)
WHERE NOT EXISTS (
    SELECT 1 FROM public.suppliers WHERE suppliers.id = new_suppliers.id
);

-- ============================================
-- TABLA: PRODUCTS (Productos)
-- ============================================
INSERT INTO public.products (id, active, created_at, updated_at, name, description, price, stock, minimum_stock, supplier_id, category, unit_measure, unit_sale)
SELECT * FROM (VALUES
                   (1, TRUE, '2024-01-15'::date, '2024-12-10'::date, 'Leche Entera 1L', 'Leche de vaca fresca y pasteurizada', 1.15, 80, 50, 1, 'Lácteos', 'Litros', 'Unidad'),
                   (2, TRUE, '2024-01-15'::date, '2024-12-10'::date, 'Yogur Natural Pack x6', 'Yogures sin azúcar ni aditivos', 2.50, 120, 40, 1, 'Lácteos', 'Pack', 'Unidad'),
                   (3, TRUE, '2024-02-01'::date, '2024-12-10'::date, 'Filete de Ternera 500g', 'Corte magro y tierno, envasado al vacío', 8.99, 45, 20, 2, 'Carnes', 'Gramos', 'Paquete'),
                   (4, TRUE, '2024-02-01'::date, '2024-12-10'::date, 'Pollo Entero Fresco', 'Pollo de corral, 1.5kg aprox.', 6.75, 60, 30, 2, 'Carnes', 'Kilogramos', 'Unidad'),
                   (5, TRUE, '2024-03-10'::date, '2024-12-10'::date, 'Tomate Pera 1kg', 'Tomates frescos de temporada', 1.80, 150, 60, 3, 'Frutas y Verduras', 'Kilogramos', 'Bolsa'),
                   (6, TRUE, '2024-03-10'::date, '2024-12-10'::date, 'Lechuga Romana', 'Lechuga fresca, unidad', 0.95, 200, 80, 3, 'Frutas y Verduras', 'Unidad', 'Unidad'),
                   (7, TRUE, '2024-04-05'::date, '2024-12-10'::date, 'Agua Mineral 1.5L', 'Pack de 6 botellas de agua mineral natural', 3.20, 300, 100, 4, 'Bebidas', 'Pack', 'Pack'),
                   (8, TRUE, '2024-04-05'::date, '2024-12-10'::date, 'Cerveza Lager Pack x12', 'Cerveza rubia de sabor suave', 8.50, 180, 50, 4, 'Bebidas', 'Pack', 'Pack'),
                   (9, TRUE, '2024-05-20'::date, '2024-12-10'::date, 'Pan de Molde Blanco', 'Rebanadas grandes, sin corteza', 1.75, 90, 40, 1, 'Panadería', 'Unidad', 'Unidad'),
                   (10, TRUE, '2024-05-20'::date, '2024-12-10'::date, 'Mantequilla sin Sal 250g', 'Mantequilla de alta calidad', 3.10, 75, 30, 1, 'Lácteos', 'Gramos', 'Unidad'),
                   (11, TRUE, '2024-06-11'::date, '2024-12-10'::date, 'Detergente Ropa Color', '1.5L, fórmula concentrada', 7.45, 110, 50, 5, 'Limpieza', 'Litros', 'Botella'),
                   (12, TRUE, '2024-06-11'::date, '2024-12-10'::date, 'Desinfectante Multiusos', 'Spray 750ml, aroma pino', 2.99, 150, 60, 5, 'Limpieza', 'Mililitros', 'Botella'),
                   (13, TRUE, '2024-07-01'::date, '2024-12-10'::date, 'Pizza Margarita Congelada', 'Pizza de masa fina, 350g', 4.10, 80, 40, 6, 'Congelados', 'Gramos', 'Unidad'),
                   (14, TRUE, '2024-07-01'::date, '2024-12-10'::date, 'Helado Vainilla 1L', 'Tarrina de helado cremoso', 5.95, 95, 35, 6, 'Congelados', 'Litros', 'Tarrina'),
                   (15, TRUE, '2024-08-15'::date, '2024-12-10'::date, 'Plátanos de Canarias', '1kg, frescos, primera calidad', 2.20, 180, 70, 3, 'Frutas y Verduras', 'Kilogramos', 'Bolsa'),
                   (16, TRUE, '2024-08-15'::date, '2024-12-10'::date, 'Café Molido Natural 250g', 'Tostado natural, molido fino', 3.85, 130, 50, 4, 'Bebidas', 'Gramos', 'Paquete'),
                   (17, TRUE, '2024-09-01'::date, '2024-12-10'::date, 'Queso Curado 200g', 'Queso madurado, sabor intenso', 4.90, 65, 25, 1, 'Lácteos', 'Gramos', 'Pieza'),
                   (18, TRUE, '2024-09-01'::date, '2024-12-10'::date, 'Salchichas Frescas Pack x6', 'Salchichas de cerdo, 300g', 3.45, 90, 40, 2, 'Carnes', 'Pack', 'Pack'),
                   (19, TRUE, '2024-10-10'::date, '2024-12-10'::date, 'Refresco Cola Zero 2L', 'Bebida carbonatada sin azúcar', 1.95, 220, 80, 4, 'Bebidas', 'Litros', 'Botella'),
                   (20, TRUE, '2024-10-10'::date, '2024-12-10'::date, 'Bolsas de Basura 50L', 'Pack de 20 bolsas, resistentes', 3.70, 160, 70, 5, 'Limpieza', 'Pack', 'Pack')
              ) AS new_products(id, active, created_at, updated_at, name, description, price, stock, minimum_stock, supplier_id, category, unit_measure, unit_sale)
WHERE NOT EXISTS (
    SELECT 1 FROM public.products WHERE products.id = new_products.id
);

-- ============================================
-- TABLA: ORDERS (Pedidos)
-- ============================================
INSERT INTO public.orders (id, order_date, order_number, status, total_amount, supplier_id, user_id, observations)
SELECT * FROM (VALUES
                   (1, '2024-10-10'::date, 'P-20241010-001', 'RECEIVED', 185.00, 1, 2, 'Pedido de lácteos recibido correctamente'),
                   (2, '2024-11-01'::date, 'P-20241101-002', 'RECEIVED', 350.50, 2, 2, 'Carnes de calidad premium'),
                   (3, '2024-11-05'::date, 'P-20241105-003', 'RECEIVED', 490.00, 4, 2, 'Reposición bebidas'),
                   (4, '2024-11-10'::date, 'P-20241110-004', 'RECEIVED', 280.95, 3, 2, 'Productos frescos de temporada'),
                   (5, '2024-11-15'::date, 'P-20241115-005', 'RECEIVED', 650.40, 5, 1, 'Productos de limpieza para el mes'),
                   (6, '2024-11-20'::date, 'P-20241120-006', 'RECEIVED', 410.25, 6, 2, 'Congelados recibidos en buen estado'),
                   (7, '2024-12-05'::date, 'P-20241205-007', 'PENDING', 330.00, 1, 1, 'Pedido lácteos pendiente de recepción'),
                   (8, '2024-12-12'::date, 'P-20241212-008', 'PENDING', 580.00, 2, 1, 'Pedido carnes programado para esta semana'),
                   (9, '2024-12-13'::date, 'P-20241213-009', 'CANCELLED', 200.00, 2, 2, 'Cancelado por falta de stock del proveedor')
              ) AS new_orders(id, order_date, order_number, status, total_amount, supplier_id, user_id, observations)
WHERE NOT EXISTS (
    SELECT 1 FROM public.orders WHERE orders.id = new_orders.id
);

-- ============================================
-- TABLA: ORDER_ITEMS (Ítems de Pedido)
-- ============================================
INSERT INTO public.order_items (item_id, order_id, product_id, quantity, unit_price, sub_total)
SELECT * FROM (VALUES
                   (1, 1, 1, 100, 1.00, 100.00),
                   (2, 1, 2, 30, 2.00, 60.00),
                   (3, 1, 10, 50, 0.50, 25.00),
                   (4, 2, 3, 30, 8.50, 255.00),
                   (5, 2, 4, 14, 6.82, 95.50),
                   (6, 3, 7, 100, 3.00, 300.00),
                   (7, 3, 8, 20, 9.50, 190.00),
                   (8, 4, 5, 80, 1.50, 120.00),
                   (9, 4, 6, 100, 0.85, 85.00),
                   (10, 4, 15, 35, 2.17, 75.95),
                   (11, 5, 11, 60, 6.00, 360.00),
                   (12, 5, 12, 80, 2.50, 200.00),
                   (13, 5, 20, 20, 4.52, 90.40),
                   (14, 6, 13, 50, 3.50, 175.00),
                   (15, 6, 14, 45, 5.22, 235.25),
                   (16, 7, 1, 150, 1.10, 165.00),
                   (17, 7, 17, 50, 3.30, 165.00),
                   (18, 8, 3, 40, 10.00, 400.00),
                   (19, 8, 18, 50, 3.60, 180.00)
              ) AS new_order_items(item_id, order_id, product_id, quantity, unit_price, sub_total)
WHERE NOT EXISTS (
    SELECT 1 FROM public.order_items WHERE order_items.item_id = new_order_items.item_id
);

-- ============================================
-- TABLA: STOCK_MOVEMENTS
-- ============================================
INSERT INTO public.stock_movements (id, date, movement_type, quantity, reference, product_id, user_id)
SELECT * FROM (VALUES
                   (1, '2024-10-11'::timestamp, 'IN', 100, 'REC-P-001', 1, 2),
                   (2, '2024-10-11'::timestamp, 'IN', 30, 'REC-P-001', 2, 2),
                   (3, '2024-10-11'::timestamp, 'IN', 50, 'REC-P-001', 10, 2),
                   (4, '2024-11-02'::timestamp, 'IN', 30, 'REC-P-002', 3, 2),
                   (5, '2024-11-02'::timestamp, 'IN', 14, 'REC-P-002', 4, 2),
                   (6, '2024-11-06'::timestamp, 'IN', 100, 'REC-P-003', 7, 2),
                   (7, '2024-11-06'::timestamp, 'IN', 20, 'REC-P-003', 8, 2),
                   (8, '2024-11-11'::timestamp, 'IN', 80, 'REC-P-004', 5, 2),
                   (9, '2024-11-11'::timestamp, 'IN', 100, 'REC-P-004', 6, 2),
                   (10, '2024-11-11'::timestamp, 'IN', 35, 'REC-P-004', 15, 2),
                   (11, '2024-11-16'::timestamp, 'IN', 60, 'REC-P-005', 11, 2),
                   (12, '2024-11-16'::timestamp, 'IN', 80, 'REC-P-005', 12, 2),
                   (13, '2024-11-16'::timestamp, 'IN', 20, 'REC-P-005', 20, 2),
                   (14, '2024-11-21'::timestamp, 'IN', 50, 'REC-P-006', 13, 2),
                   (15, '2024-11-21'::timestamp, 'IN', 45, 'REC-P-006', 14, 2),
                   (16, '2024-10-20'::timestamp, 'OUT', 5, 'AJ-CADUCIDAD', 1, 1),
                   (17, '2024-10-25'::timestamp, 'OUT', 2, 'AJ-PERDIDA', 6, 2),
                   (18, '2024-11-05'::timestamp, 'OUT', 1, 'AJ-MERMA', 4, 2),
                   (19, '2024-11-12'::timestamp, 'OUT', 10, 'AJ-INVENTARIO', 5, 1),
                   (20, '2024-11-18'::timestamp, 'OUT', 5, 'AJ-CADUCIDAD', 2, 1),
                   (21, '2024-11-25'::timestamp, 'OUT', 20, 'AJ-DONACION', 7, 1),
                   (22, '2024-11-25'::timestamp, 'OUT', 3, 'AJ-ROBO', 3, 2),
                   (23, '2024-12-01'::timestamp, 'OUT', 6, 'AJ-MERMA', 15, 2),
                   (24, '2024-12-10'::timestamp, 'OUT', 10, 'AJ-ROTURA', 12, 1)
              ) AS new_movements(id, date, movement_type, quantity, reference, product_id, user_id)
WHERE NOT EXISTS (
    SELECT 1 FROM public.stock_movements WHERE stock_movements.id = new_movements.id
);

-- REINICIO DE SECUENCIAS (Importante tras insertar IDs manuales)
SELECT setval(pg_get_serial_sequence('public.suppliers', 'id'), coalesce(max(id), 1)) FROM public.suppliers;
SELECT setval(pg_get_serial_sequence('public.products', 'id'), coalesce(max(id), 1)) FROM public.products;
SELECT setval(pg_get_serial_sequence('public.orders', 'id'), coalesce(max(id), 1)) FROM public.orders;
SELECT setval(pg_get_serial_sequence('public.order_items', 'item_id'), coalesce(max(item_id), 1)) FROM public.order_items;
SELECT setval(pg_get_serial_sequence('public.stock_movements', 'id'), coalesce(max(id), 1)) FROM public.stock_movements;

COMMIT;

-- Mensaje de confirmación
DO $$
BEGIN
    RAISE NOTICE 'Script ejecutado. Datos de prueba cargados correctamente.';
END $$;