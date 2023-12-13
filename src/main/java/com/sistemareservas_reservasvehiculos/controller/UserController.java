package com.sistemareservas_reservasvehiculos.controller;


import com.sistemareservas_reservasvehiculos.domain.dto.UserDto;
import com.sistemareservas_reservasvehiculos.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@SecurityRequirement(name = "BearerAuth")
public record UserController(
        UserService userService
) {

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> searchAll() {
        return new ResponseEntity<>(userService.userList(), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchUser(@PathVariable("id") Integer id) {
        return new  ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto){
        userService.updateUser(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
