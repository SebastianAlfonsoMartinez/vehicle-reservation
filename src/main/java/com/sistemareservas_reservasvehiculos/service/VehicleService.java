package com.sistemareservas_reservasvehiculos.service;

import com.sistemareservas_reservasvehiculos.domain.entity.Vehicle;
import com.sistemareservas_reservasvehiculos.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public record VehicleService(
        VehicleRepository vehicleRepository) {

    public void createVehicle(Vehicle vehicle){vehicleRepository.save(vehicle);}

    public List<Vehicle> vehicleList(){return  vehicleRepository.findAll();}

    public Vehicle findVehicleById(Integer id){
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found..."));
    }

    public void deleteVehicle(Integer id){
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found..."));
        vehicleRepository.delete(vehicle);
    }

    public void updateVehicle(Vehicle vehicle){
        vehicleRepository.findById(vehicle.getId()).orElseThrow(() -> new RuntimeException("Vehicle not found..."));
        vehicleRepository.save(vehicle);
    }
}
