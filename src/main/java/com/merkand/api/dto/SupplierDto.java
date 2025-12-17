package com.merkand.api.dto;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    @Id
    private Long id;
    private String nif;
    private String name;
    private String contactName;
    private String phone;
    private String email;
    private String address;
    private boolean active;
    private List<ProductDto> productList;
    private List<OrderDto> orderList;
}
