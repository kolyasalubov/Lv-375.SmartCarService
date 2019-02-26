package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByUserId(Long id);

    List<Car>findByUser_Username(String username);

    Car findByVin(String vin);

    Car getCarById(Long id);

    Car findByNumber (String number);

 List<Car>findAllByDealer(DealerEntity dealerEntity);

 List<Car> findAllByDealerDealerEdr(String edr);

 }

