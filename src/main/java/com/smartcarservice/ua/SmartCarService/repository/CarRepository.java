package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcarservice.ua.SmartCarService.entity.Car;
import com.smartcarservice.ua.SmartCarService.entity.CarOwner;

public interface CarRepository extends JpaRepository<Car, Long> {

	Car create(Car car);

	Car updateCar(Car car);

	List<Car> findAll();

	Car findById(Long id);

	List<Car> findByOwnerId(Long id);

	void deleteById(Long id);

}
