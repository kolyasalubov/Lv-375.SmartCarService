package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by 1 on 05.02.2019.
 */
public interface DealerRepository extends JpaRepository<Dealer,Long> {

    Dealer findDealerByTechnicalServices(TechnicalService technicalService);
    Dealer findBySalesManagers(SalesManager salesManager);

    Dealer findDealerByCars(Car car);

Dealer getDealerById(Long id);

//    @Query("select d from Dealer d where d.id=:id")
//    Dealer getDealerById(@Param("id") long id);

}
