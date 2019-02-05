package ua.ita.smartcarservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.car.Car;


public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByCarOwnerId(Long id);

    Car findByVin(String vin);

    Car getCarById(Long id);

}
