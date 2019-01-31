package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

	CarOwner findByUserName (String userName);
	
}
