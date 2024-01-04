package com.sistemareservas_reservasvehiculos.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sistemareservas_reservasvehiculos.aplication.lasting.ERole;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserDto(
        Integer id,
        String firstName,
        String lastName,
        @JsonIgnore
        String password,
        String email,
        String phone,
        Boolean enable,
        ERole role
) {
}
