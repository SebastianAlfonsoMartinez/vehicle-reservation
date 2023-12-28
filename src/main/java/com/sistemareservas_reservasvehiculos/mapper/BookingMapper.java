package com.sistemareservas_reservasvehiculos.mapper;

import com.sistemareservas_reservasvehiculos.domain.dto.BookingDto;
import com.sistemareservas_reservasvehiculos.domain.entity.Booking;
import com.sistemareservas_reservasvehiculos.domain.entity.Vehicle;
import com.sistemareservas_reservasvehiculos.mapper.base.IBaseMapper;
import com.sistemareservas_reservasvehiculos.repository.UserRepository;
import com.sistemareservas_reservasvehiculos.repository.VehicleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper extends IBaseMapper {

    Booking toEntity(BookingDto dto);

    BookingDto toDto(Booking entity);

    List<Booking> toEntityList(List<BookingDto> dtoList);

    List<BookingDto> toDtoList(List<Booking> entityList);

}