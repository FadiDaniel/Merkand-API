package com.merkand.api.entity;

import com.merkand.api.entity.enums.MovementType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_movements")
public class StockMovement {
    @Id
    private Long id;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private MovementType movementType; // "IN", "OUT", "ADJUST"
    private String reference;
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
