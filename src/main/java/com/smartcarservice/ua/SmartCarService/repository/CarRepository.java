package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;

public interface CarRepository extends JpaRepository<Car, Long> {
	
	//Transfer to DTO should be here
	List<Car> findByCarOwnerId(Long id);
	Car findByVin (String vin);

}
