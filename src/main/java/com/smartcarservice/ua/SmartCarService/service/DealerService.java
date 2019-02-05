package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;

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
