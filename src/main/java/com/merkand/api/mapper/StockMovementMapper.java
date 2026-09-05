package com.merkand.api.mapper;

import com.merkand.api.dto.MovementItemDto;
import com.merkand.api.dto.StockMovementDto;
import com.merkand.api.entity.MovementItem;
import com.merkand.api.entity.StockMovement;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between StockMovement entity and StockMovementDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 */
@Mapper(componentModel = "spring", uses = {ProductMapper.class, UserMapper.class, MovementItemMapper.class})
public interface StockMovementMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
    StockMovementDto toDto(StockMovement entity);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.username")
    StockMovement toEntity(StockMovementDto dto);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.username")
    void updateEntityFromDto(StockMovementDto dto, @MappingTarget StockMovement entity);

    List<StockMovementDto> toDtoList(List<StockMovement> entities);

    List<StockMovement> toEntityList(List<StockMovementDto> dtos);
}