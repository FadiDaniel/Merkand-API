package com.merkand.api.mapper;

import com.merkand.api.dto.OrderDto;
import com.merkand.api.entity.Order;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between Order entity and OrderDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 * Note: orderItemList mapping is handled by OrderItemMapper to prevent circular references.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class, UserMapper.class, OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "supplier.name", target = "supplierName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    OrderDto toDto(Order entity);

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "supplierName", target = "supplier.name")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.username")
    @Mapping(target = "orderItemList", ignore = true)
    Order toEntity(OrderDto dto);

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "supplierName", target = "supplier.name")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.username")
    @Mapping(target = "orderItemList", ignore = true)
    void updateEntityFromDto(OrderDto dto, @MappingTarget Order entity);

    List<OrderDto> toDtoList(List<Order> entities);

    List<Order> toEntityList(List<OrderDto> dtos);
}