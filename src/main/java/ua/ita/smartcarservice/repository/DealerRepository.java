package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;

/**
 * Created by 1 on 05.02.2019.
 */
public interface DealerRepository extends JpaRepository<Dealer,Long> {

    Dealer findDealerByTechnicalServices(TechnicalService technicalService);
    Dealer findBySalesManagers(SalesManager salesManager);
//    Dealer getDealerByUserName(String name);

    Dealer findDealerByCars(Car car);

Dealer getDealerById(Long id);

//    @Query("select d from Dealer d where d.id=:id")
//    Dealer getDealerById(@Param("id") long id);

}
