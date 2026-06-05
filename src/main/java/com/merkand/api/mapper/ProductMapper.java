package com.merkand.api.mapper;

import com.merkand.api.dto.ProductDto;
import com.merkand.api.entity.Product;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between Product entity and ProductDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 */
@Mapper(componentModel = "spring", uses = {SupplierMapper.class})
public interface ProductMapper {

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "supplier.name", target = "supplierName")
    ProductDto toDto(Product entity);

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "supplierName", target = "supplier.name")
    Product toEntity(ProductDto dto);

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "supplierName", target = "supplier.name")
    void updateEntityFromDto(ProductDto dto, @MappingTarget Product entity);

    List<ProductDto> toDtoList(List<Product> entities);

    List<Product> toEntityList(List<ProductDto> dtos);
}