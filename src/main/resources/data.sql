 INSERT INTO public.suppliers (id, active, name, contact_name, phone, email, address) VALUES
                                                                                          (1, TRUE, 'Distribuidora Láctea del Sur', 'Ana Gómez', '555-1234', 'contacto@lacteasur.com', 'Calle Mayor 10, Madrid'),
                                                                                          (2, TRUE, 'Carnes Premium S.A.', 'Roberto Funes', '555-5678', 'ventas@carnepremium.com', 'Av. Central 50, Barcelona'),
                                                                                          (3, TRUE, 'Alimentos Frescos Hnos.', 'Carlos Ruiz', '555-9012', 'pedidos@frescoshnos.com', 'Polígono Industrial 3, Valencia'),
                                                                                          (4, TRUE, 'Bebidas Mundiales Ltda.', 'Marta Vidal', '555-3456', 'info@bebidasmundiales.com', 'Ronda Exterior 25, Sevilla'),
                                                                                          (5, TRUE, 'Fitosanitarios y Limpieza S.L.', 'Elena Soto', '555-7788', 'contacto@limpiezas.es', 'Polígono Calle C, Bilbao'),
                                                                                          (6, TRUE, 'Productos Congelados Rápidos', 'Javier Cano', '555-2020', 'ventas@congeladosrapidos.net', 'Av. del Puerto 15, Cádiz');

 INSERT INTO public.products (id, active, created_at, updated_at, name, description, price, stock, minimum_stock, supplier_id, category) VALUES
                                                                                                                                             (1, TRUE, '2024-01-15', '2024-12-10', 'Leche Entera 1L', 'Leche de vaca fresca y pasteurizada', 1.15, 80, 50, 1, 'Lácteos'),
                                                                                                                                             (2, TRUE, '2024-01-15', '2024-12-10', 'Yogur Natural Pack x6', 'Yogures sin azúcar ni aditivos', 2.50, 120, 40, 1, 'Lácteos'),
                                                                                                                                             (3, TRUE, '2024-02-01', '2024-12-10', 'Filete de Ternera 500g', 'Corte magro y tierno, envasado al vacío', 8.99, 45, 20, 2, 'Carnes'),
                                                                                                                                             (4, TRUE, '2024-02-01', '2024-12-10', 'Pollo Entero Fresco', 'Pollo de corral, 1.5kg aprox.', 6.75, 60, 30, 2, 'Carnes'),
                                                                                                                                             (5, TRUE, '2024-03-10', '2024-12-10', 'Tomate Pera 1kg', 'Tomates frescos de temporada', 1.80, 150, 60, 3, 'Frutas y Verduras'),
                                                                                                                                             (6, TRUE, '2024-03-10', '2024-12-10', 'Lechuga Romana', 'Lechuga fresca, unidad', 0.95, 200, 80, 3, 'Frutas y Verduras'),
                                                                                                                                             (7, TRUE, '2024-04-05', '2024-12-10', 'Agua Mineral 1.5L', 'Pack de 6 botellas de agua mineral natural', 3.20, 300, 100, 4, 'Bebidas'),
                                                                                                                                             (8, TRUE, '2024-04-05', '2024-12-10', 'Cerveza Lager Pack x12', 'Cerveza rubia de sabor suave', 8.50, 180, 50, 4, 'Bebidas'),
                                                                                                                                             (9, TRUE, '2024-05-20', '2024-12-10', 'Pan de Molde Blanco', 'Rebanadas grandes, sin corteza', 1.75, 90, 40, 1, 'Panadería'),
                                                                                                                                             (10, TRUE, '2024-05-20', '2024-12-10', 'Mantequilla sin Sal 250g', 'Mantequilla de alta calidad', 3.10, 75, 30, 1, 'Lácteos'),
                                                                                                                                             (11, TRUE, '2024-06-11', '2024-12-10', 'Detergente Ropa Color', '1.5L, fórmula concentrada', 7.45, 110, 50, 5, 'Limpieza'),
                                                                                                                                             (12, TRUE, '2024-06-11', '2024-12-10', 'Desinfectante Multiusos', 'Spray 750ml, aroma pino', 2.99, 150, 60, 5, 'Limpieza'),
                                                                                                                                             (13, TRUE, '2024-07-01', '2024-12-10', 'Pizza Margarita Congelada', 'Pizza de masa fina, 350g', 4.10, 80, 40, 6, 'Congelados'),
                                                                                                                                             (14, TRUE, '2024-07-01', '2024-12-10', 'Helado Vainilla 1L', 'Tarrina de helado cremoso', 5.95, 95, 35, 6, 'Congelados'),
                                                                                                                                             (15, TRUE, '2024-08-15', '2024-12-10', 'Plátanos de Canarias', '1kg, frescos, primera calidad', 2.20, 180, 70, 3, 'Frutas y Verduras'),
                                                                                                                                             (16, TRUE, '2024-08-15', '2024-12-10', 'Café Molido Natural 250g', 'Tostado natural, molido fino', 3.85, 130, 50, 4, 'Bebidas'),
                                                                                                                                             (17, TRUE, '2024-09-01', '2024-12-10', 'Queso Curado 200g', 'Queso madurado, sabor intenso', 4.90, 65, 25, 1, 'Lácteos'),
                                                                                                                                             (18, TRUE, '2024-09-01', '2024-12-10', 'Salchichas Frescas Pack x6', 'Salchichas de cerdo, 300g', 3.45, 90, 40, 2, 'Carnes'),
                                                                                                                                             (19, TRUE, '2024-10-10', '2024-12-10', 'Refresco Cola Zero 2L', 'Bebida carbonatada sin azúcar', 1.95, 220, 80, 4, 'Bebidas'),
                                                                                                                                             (20, TRUE, '2024-10-10', '2024-12-10', 'Bolsas de Basura 50L', 'Pack de 20 bolsas, resistentes', 3.70, 160, 70, 5, 'Limpieza');

 INSERT INTO public.orders (id, order_date, order_number, status, total_amount, supplier_id) VALUES
                                                                                                 (1, '2024-10-10', 'P-20241010-001', 'RECEIVED', 185.00, 1), -- Lácteos
                                                                                                 (2, '2024-11-01', 'P-20241101-002', 'RECEIVED', 350.50, 2), -- Carnes
                                                                                                 (3, '2024-11-05', 'P-20241105-003', 'RECEIVED', 490.00, 4), -- Bebidas
                                                                                                 (4, '2024-11-10', 'P-20241110-004', 'RECEIVED', 280.95, 3), -- Frescos
                                                                                                 (5, '2024-11-15', 'P-20241115-005', 'RECEIVED', 650.40, 5), -- Limpieza
                                                                                                 (6, '2024-11-20', 'P-20241120-006', 'RECEIVED', 410.25, 6), -- Congelados
                                                                                                 (7, '2024-12-05', 'P-20241205-007', 'PENDING', 330.00, 1),  -- Lácteos (Pendiente)
                                                                                                 (8, '2024-12-12', 'P-20241212-008', 'PENDING', 580.00, 2),  -- Carnes (Pendiente)
                                                                                                 (9, '2024-12-13', 'P-20241212-009', 'CANCELLED', 200.00, 2);  -- Carnes (Cancelado)

 INSERT INTO public.order_items (id, order_id, product_id, quantity, unit_price, sub_total) VALUES
 -- Pedido 1 (Lácteos - 2024-10-10)
 (1, 1, 1, 100, 1.00, 100.00), (2, 1, 2, 30, 2.00, 60.00), (3, 1, 10, 50, 0.50, 25.00),
 -- Pedido 2 (Carnes - 2024-11-01)
 (4, 2, 3, 30, 8.50, 255.00), (5, 2, 4, 14, 6.82, 95.50),
 -- Pedido 3 (Bebidas - 2024-11-05)
 (6, 3, 7, 100, 3.00, 300.00), (7, 3, 8, 20, 9.50, 190.00),
 -- Pedido 4 (Frescos - 2024-11-10)
 (8, 4, 5, 80, 1.50, 120.00), (9, 4, 6, 100, 0.85, 85.00), (10, 4, 15, 35, 2.17, 75.95),
 -- Pedido 5 (Limpieza - 2024-11-15)
 (11, 5, 11, 60, 6.00, 360.00), (12, 5, 12, 80, 2.50, 200.00), (13, 5, 20, 20, 4.52, 90.40),
 -- Pedido 6 (Congelados - 2024-11-20)
 (14, 6, 13, 50, 3.50, 175.00), (15, 6, 14, 45, 5.22, 235.25),
 -- Pedido 7 (Lácteos - 2024-12-05 - Pendiente)
 (16, 7, 1, 150, 1.10, 165.00), (17, 7, 17, 50, 3.30, 165.00),
 -- Pedido 8 (Carnes - 2024-12-12 - Pendiente)
 (18, 8, 3, 40, 10.00, 400.00), (19, 8, 18, 50, 3.60, 180.00);

 INSERT INTO public.stock_movements (created_at, movement_type, quantity, reference, product_id, user_id) VALUES
 -- RECEPCIÓN DE PEDIDOS COMPLETADOS (IN)
 ('2024-10-11', 'IN', 100, 'REC-P-001', 1, 2), -- Leche
 ('2024-10-11', 'IN', 30, 'REC-P-001', 2, 2),  -- Yogur
 ('2024-10-11', 'IN', 50, 'REC-P-001', 10, 2), -- Mantequilla
 ('2024-11-02', 'IN', 30, 'REC-P-002', 3, 2),  -- Ternera
 ('2024-11-02', 'IN', 14, 'REC-P-002', 4, 2),  -- Pollo
 ('2024-11-06', 'IN', 100, 'REC-P-003', 7, 2), -- Agua
 ('2024-11-06', 'IN', 20, 'REC-P-003', 8, 2),  -- Cerveza
 ('2024-11-11', 'IN', 80, 'REC-P-004', 5, 2),  -- Tomate
 ('2024-11-11', 'IN', 100, 'REC-P-004', 6, 2), -- Lechuga
 ('2024-11-11', 'IN', 35, 'REC-P-004', 15, 2), -- Plátanos
 ('2024-11-16', 'IN', 60, 'REC-P-005', 11, 2), -- Detergente
 ('2024-11-16', 'IN', 80, 'REC-P-005', 12, 2), -- Desinfectante
 ('2024-11-16', 'IN', 20, 'REC-P-005', 20, 2), -- Bolsas Basura
 ('2024-11-21', 'IN', 50, 'REC-P-006', 13, 2), -- Pizza Congelada
 ('2024-11-21', 'IN', 45, 'REC-P-006', 14, 2), -- Helado Vainilla

 -- AJUSTES Y SALIDAS DE INVENTARIO (OUT)
 ('2024-10-20', 'OUT', 5, 'AJ-CADUCIDAD', 1, 1),   -- Leche (Admin)
 ('2024-10-25', 'OUT', 2, 'AJ-PERDIDA', 6, 2),     -- Lechuga (Operador)
 ('2024-11-05', 'OUT', 1, 'AJ-MERMA', 4, 2),       -- Pollo (Operador)
 ('2024-11-12', 'OUT', 10, 'AJ-INVENTARIO', 5, 1), -- Tomate (Admin)
 ('2024-11-18', 'OUT', 5, 'AJ-CADUCIDAD', 2, 1),   -- Yogur (Admin)
 ('2024-11-25', 'OUT', 20, 'AJ-DONACION', 7, 1),   -- Agua Mineral (Admin)
 ('2024-11-25', 'OUT', 3, 'AJ-ROBO', 3, 2),        -- Ternera (Operador)
 ('2024-12-01', 'OUT', 6, 'AJ-MERMA', 15, 2),      -- Plátanos (Operador)
 ('2024-12-10', 'OUT', 10, 'AJ-ROTURA', 12, 1);    -- Desinfectante (Admin)