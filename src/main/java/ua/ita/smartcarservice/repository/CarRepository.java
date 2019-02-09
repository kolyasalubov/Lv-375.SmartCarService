package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.Car;

import java.util.List;


public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByUserId(Long id);

    Car findByVin(String vin);

    Car getCarById(Long id);

 }
