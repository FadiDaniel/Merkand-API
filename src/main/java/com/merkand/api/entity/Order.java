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
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    private Long id;
    private String orderNumber;
    private double totalAmount;
    private LocalDate orderDate;
    private String observations;
    @Enumerated(EnumType.STRING)
    private Status status; // "PENDING", "RECEIVED", "CANCELLED"

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

}
