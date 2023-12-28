package com.sistemareservas_reservasvehiculos.mapper;

import com.sistemareservas_reservasvehiculos.domain.dto.UserDto;
import com.sistemareservas_reservasvehiculos.domain.entity.User;
import com.sistemareservas_reservasvehiculos.mapper.base.IBaseMapper;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends IBaseMapper {

    User toEntity(UserDto dto);

    UserDto toDto(User entity);

    List<User> toEntityList(List<UserDto> dtoList);

    List<UserDto> toDtoList(List<User> entityList);

}
