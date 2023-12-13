package com.sistemareservas_reservasvehiculos.service;

import com.sistemareservas_reservasvehiculos.domain.dto.UserDto;
import com.sistemareservas_reservasvehiculos.domain.entity.User;
import com.sistemareservas_reservasvehiculos.lasting.ERole;
import com.sistemareservas_reservasvehiculos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
) {

    public void createUser(UserDto userDto) {
        User user = User.builder()
                .id(userDto.id())
                .firstName(userDto.firstName())
                .lastName(userDto.lastName())
                .password(userDto.password())
                .email(userDto.email())
                .phone(userDto.phone())
                .enable(userDto.enable())
                .role(ERole.USER)
                .enable(true)
                .build();
        userRepository.save(user);
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found..."));
    }

    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found..."));
        userRepository.delete(user);
    }

    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.id())
                .orElseThrow(() -> new RuntimeException("User not found..."));
        updateUserData(user, userDto);
        userRepository.save(user);
    }

    private void updateUserData(User user, UserDto userDto) {

        user.setId(userDto.id());
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setEmail(userDto.email());
        user.setPhone(userDto.phone());
        user.setEnable(userDto.enable());
        user.setRole(ERole.USER);
        user.setEnable(userDto.enable());
    }

}

