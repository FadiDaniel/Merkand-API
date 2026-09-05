package com.merkand.api.mapper;

import com.merkand.api.dto.MovementItemDto;
import com.merkand.api.entity.MovementItem;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between MovementItem entity and MovementItemDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 */
@Mapper(componentModel = "spring")
public interface MovementItemMapper {

    @Mapping(source = "product.id", target = "productId")
    MovementItemDto toDto(MovementItem entity);

    @Mapping(source = "productId", target = "product.id")
    MovementItem toEntity(MovementItemDto dto);

    void updateEntityFromDto(MovementItemDto dto, @MappingTarget MovementItem entity);

    List<MovementItemDto> toDtoList(List<MovementItem> entities);

    List<MovementItem> toEntityList(List<MovementItemDto> dtos);
}