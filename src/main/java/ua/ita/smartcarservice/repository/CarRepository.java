package ua.ita.smartcarservice.repository;

import java.util.List;
import ua.ita.smartcarservice.entity.car.Car;
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
