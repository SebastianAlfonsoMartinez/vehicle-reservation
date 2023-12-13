package com.sistemareservas_reservasvehiculos.controller;

import com.sistemareservas_reservasvehiculos.domain.dto.VehicleDto;
import com.sistemareservas_reservasvehiculos.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public record VehicleController(
        VehicleService vehicleService
) {

    @PostMapping("/create")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<?> createVehicle(@RequestBody VehicleDto vehicleDto) {
        vehicleService.createVehicle(vehicleDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    @Operation(security = {@SecurityRequirement(name = "BearerAuth")})
    public ResponseEntity<?> searchAll() {
        return new ResponseEntity<>(vehicleService.vehicleList(), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<?> searchVehicle(@PathVariable("id") Integer id) {
                return new  ResponseEntity<>(vehicleService.findVehicleById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "BearerAuth")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) {
        vehicleService.deleteVehicle(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVehicle(@RequestBody VehicleDto vehicleDto){
        vehicleService.updateVehicle(vehicleDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
