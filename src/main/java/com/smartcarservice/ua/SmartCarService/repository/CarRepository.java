package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;


public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCarOwnerId(Long id);

    Car findByVin(String vin);

    Car getCarById(Long id);

}
