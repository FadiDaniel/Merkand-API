package com.merkand.api.mapper;

import com.merkand.api.dto.OrderItemDto;
import com.merkand.api.entity.OrderItem;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between OrderItem entity and OrderItemDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 */
@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "order.id", target = "orderId")
    @Mapping(source = "order.orderNumber", target = "orderNumber")
    OrderItemDto toDto(OrderItem entity);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productName", target = "product.name")
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "orderNumber", target = "order.orderNumber")
    OrderItem toEntity(OrderItemDto dto);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "productName", target = "product.name")
    @Mapping(source = "orderId", target = "order.id")
    @Mapping(source = "orderNumber", target = "order.orderNumber")
    void updateEntityFromDto(OrderItemDto dto, @MappingTarget OrderItem entity);

    List<OrderItemDto> toDtoList(List<OrderItem> entities);

    List<OrderItem> toEntityList(List<OrderItemDto> dtos);
}