package com.sistemareservas_reservasvehiculos.aplication.controller;

import com.sistemareservas_reservasvehiculos.domain.dto.VehicleDto;
import com.sistemareservas_reservasvehiculos.aplication.exception.BookingException;
import com.sistemareservas_reservasvehiculos.aplication.service.VehicleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
public record VehicleController(
        VehicleService vehicleService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.createVehicle(vehicleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all/{offset}/{limit}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> searchAll(
            @PathVariable("offset") Integer offset,
            @PathVariable("limit") Integer limit) throws BookingException {
        List<VehicleDto> vehicles = vehicleService.vehicleList(offset, limit);
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> searchVehicle(@PathVariable("id") Integer id) throws BookingException {
                return new  ResponseEntity<>(vehicleService.findVehicleById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) throws BookingException {
        vehicleService.deleteVehicle(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> updateVehicle(
            @PathVariable("id") Integer id,
            @RequestBody VehicleDto vehicleDto) throws BookingException {
        vehicleService.updateVehicle(id, vehicleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
