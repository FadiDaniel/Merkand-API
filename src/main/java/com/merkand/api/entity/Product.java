package com.merkand.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private boolean active;
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private ArrayList<OrderItem> orderItemList = new ArrayList<OrderItem>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private ArrayList<StockMovement> stockMovementList = new ArrayList<StockMovement>();
}
