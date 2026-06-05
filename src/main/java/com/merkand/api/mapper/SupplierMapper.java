package com.merkand.api.mapper;

import com.merkand.api.dto.SupplierDto;
import com.merkand.api.entity.Supplier;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between Supplier entity and SupplierDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 * Note: productList mapping is ignored to prevent circular references.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface SupplierMapper {

    @Mapping(target = "productList", ignore = true)
    SupplierDto toDto(Supplier entity);

    @Mapping(target = "productList", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    Supplier toEntity(SupplierDto dto);

    @Mapping(target = "productList", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    void updateEntityFromDto(SupplierDto dto, @MappingTarget Supplier entity);

    List<SupplierDto> toDtoList(List<Supplier> entities);

    List<Supplier> toEntityList(List<SupplierDto> dtos);

    // Ignore the productList and orderList to avoid circular references during mapping
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "productList", ignore = true)
    @Mapping(target = "orderList", ignore = true)
    Supplier updateEntityFromDtoIgnoringLists(SupplierDto dto, @MappingTarget Supplier entity);
}