package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
    Dealer getDealerByUserName(String username);

    Dealer findByUserName(String userame);

    Dealer findDealerByTechnicalServices(TechnicalService technicalService);

    Dealer findBySalesManagers(SalesManager salesManager);

    Dealer findDealerByCars(Car car);

    Dealer getDealerById(Long id);
}
