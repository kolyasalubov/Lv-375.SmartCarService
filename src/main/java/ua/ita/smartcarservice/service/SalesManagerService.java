package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;

import java.util.Set;



public interface SalesManagerService {

    void createSaleManager(SalesManager salesManager);

    void deleteSalesManager(SalesManager salesManager);

    void updateSalesManager(SalesManager salesManager);

    void deleteSalesManagerbyId(Long id);

    Set<SalesManager> findAll();

    Set<SalesManager>findAllByDealer(Dealer dealer);


    Set<SalesManager>findAllByDealerId(Long id);


}
