package com.merkand.api.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "suppliers")
public class Supplier {
    @Id
    private Long id;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private boolean active;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

}
