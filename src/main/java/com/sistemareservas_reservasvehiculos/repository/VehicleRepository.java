package com.sistemareservas_reservasvehiculos.repository;

import com.sistemareservas_reservasvehiculos.domain.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {

    //consultas SQL
}
