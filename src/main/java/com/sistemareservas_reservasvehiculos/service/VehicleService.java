package com.sistemareservas_reservasvehiculos.service;

import com.sistemareservas_reservasvehiculos.domain.dto.VehicleDto;
import com.sistemareservas_reservasvehiculos.domain.entity.Vehicle;
import com.sistemareservas_reservasvehiculos.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record VehicleService(
        VehicleRepository vehicleRepository) {

    public void createVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = Vehicle.builder()
                .brand(vehicleDto.brand())
                .typeVehicle(vehicleDto.typeVehicle())
                .manufactureYear(vehicleDto.manufactureYear())
                .color(vehicleDto.color())
                .typeTransmission(vehicleDto.typeTransmission())
                .numberDoors(vehicleDto.numberDoors())
                .typeFuel(vehicleDto.typeFuel())
                .price(vehicleDto.price())
                .available(vehicleDto.available())
                .build();
        vehicleRepository.save(vehicle);
    }

    public List<Vehicle> vehicleList() {
        return vehicleRepository.findAll();
    }

    public Vehicle findVehicleById(Integer id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found..."));
    }

    public void deleteVehicle(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new RuntimeException("Vehicle not found..."));
        vehicleRepository.delete(vehicle);
    }

    public void updateVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleDto.id())
                .orElseThrow(() -> new RuntimeException("Vehicle not found..."));
        updateVehicleData(vehicle, vehicleDto);
        vehicleRepository.save(vehicle);
    }


    private void updateVehicleData(Vehicle vehicle, VehicleDto vehicleDto) {

        vehicle.setBrand(vehicleDto.brand());
        vehicle.setTypeVehicle(vehicleDto.typeVehicle());
        vehicle.setManufactureYear(vehicleDto.manufactureYear());
        vehicle.setColor(vehicleDto.color());
        vehicle.setTypeTransmission(vehicleDto.typeTransmission());
        vehicle.setNumberDoors(vehicleDto.numberDoors());
        vehicle.setTypeFuel(vehicleDto.typeFuel());
        vehicle.setPrice(vehicleDto.price());
        vehicle.setAvailable(vehicleDto.available());
    }


}
