package ua.ita.smartcarservice.service;


import ua.ita.smartcarservice.entity.car.Car;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sto.TechnicalService;

import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 05.02.2019.
 */
public interface DealerService  {

    void CreateDealer(Dealer dealer);

    void DeleteDealer(Dealer dealer);

    void DeleteDealerById(Long id);

    void UpdateDealer(Dealer dealer);

    Dealer findById(Long id);

   List<Dealer> findAll();

    Dealer findDealerBySaleManager(SalesManager salesManager);

    Dealer findDealerByCar(Car car);

//    Dealer findDealerByLogin(String login);

    Dealer findDealerBySto(TechnicalService technicalService);

}
