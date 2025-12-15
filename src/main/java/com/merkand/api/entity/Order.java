package com.merkand.api.entity;

import com.merkand.api.entity.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
    private String orderNumber;
    private double totalAmount;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private Status status; // "PENDING", "COMPLETED", "CANCELLED"

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList;

}
