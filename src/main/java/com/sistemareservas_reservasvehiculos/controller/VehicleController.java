package com.sistemareservas_reservasvehiculos.controller;

import com.sistemareservas_reservasvehiculos.domain.entity.Vehicle;
import com.sistemareservas_reservasvehiculos.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicle")
public record VehicleController(
        VehicleService vehicleService
) {

    @PostMapping("/create")
    public ResponseEntity<?> createVehicle(@RequestBody Vehicle vehicle) {
        vehicleService.createVehicle(vehicle);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<?> searchAll() {
        return new ResponseEntity<>(vehicleService.vehicleList(), HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> searchVehicle(@PathVariable("id") Integer id) {
                return new  ResponseEntity<>(vehicleService.findVehicleById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) {
        vehicleService.deleteVehicle(id);
        return new  ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle){
        vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
