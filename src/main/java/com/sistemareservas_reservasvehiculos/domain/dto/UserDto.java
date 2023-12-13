package com.sistemareservas_reservasvehiculos.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sistemareservas_reservasvehiculos.lasting.ERole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
Integer id,
String firstName,
String lastName,
String password,
String email,
String phone,
Boolean enable,
ERole role
) {
}
