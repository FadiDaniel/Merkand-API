package com.merkand.api.mapper;

import com.merkand.api.dto.UserDto;
import com.merkand.api.entity.User;
import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for converting between User entity and UserDto.
 * Uses MapStruct for type-safe, compile-time mapping.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(User entity);

    User toEntity(UserDto dto);

    void updateEntityFromDto(UserDto dto, @MappingTarget User entity);

    List<UserDto> toDtoList(List<User> entities);

    List<User> toEntityList(List<UserDto> dtos);
}