package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcarservice.ua.SmartCarService.entity.Car;
import com.smartcarservice.ua.SmartCarService.entity.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

	CarOwner create(CarOwner carOwner);

	CarOwner updateCarOwner(CarOwner carOwner);

	List<CarOwner> findAll();

	CarOwner findByEmail(String email);

	CarOwner findById(Long id);

	void deleteById(Long id);

}
