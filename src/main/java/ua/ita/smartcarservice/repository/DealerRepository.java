package ua.ita.smartcarservice.repository;

import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;
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
