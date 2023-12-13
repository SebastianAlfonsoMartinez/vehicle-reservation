package com.sistemareservas_reservasvehiculos.service;


import com.sistemareservas_reservasvehiculos.domain.dto.AuthenticationDto;
import com.sistemareservas_reservasvehiculos.domain.dto.UserDto;
import com.sistemareservas_reservasvehiculos.domain.entity.User;
import com.sistemareservas_reservasvehiculos.lasting.ERole;
import com.sistemareservas_reservasvehiculos.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthenticationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        JwtService jwtService,
        AuthenticationManager authenticationManager

) {

    public String register(UserDto userDto){
        User user = User.builder()
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .email(userDto.email())
                .enable(true)
                .password(passwordEncoder.encode(userDto.password()))
                .phone(userDto.phone())
                .role(ERole.USER)
                .enable(true)
                .build();
        userRepository.save(user);
        return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationDto authenticationDto){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.email(),
                        authenticationDto.password()
                )
        );
        User user = userRepository.findUserByEmail(authenticationDto.email()).orElseThrow();
        return jwtService.generateToken(user);
    }

}
