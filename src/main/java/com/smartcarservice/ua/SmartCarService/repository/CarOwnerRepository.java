package com.smartcarservice.ua.SmartCarService.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;

public interface CarOwnerRepository extends JpaRepository<CarOwner, Long> {

    CarOwner findByUserName(String userName);

    CarOwner getCarOwnerById(Long id);

}
