package com.smartcarservice.ua.SmartCarService.repository;


import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>{


    List<Car> findByCarOwnerId(Long id);

    Car findByVin(String vin);

    Car getCarById(Long id);

    List<Car> getAllByDealer(Dealer dealer);

}
