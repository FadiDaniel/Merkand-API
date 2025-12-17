package com.merkand.api.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;

import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private boolean active;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> productList;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Order> orderList;

}
