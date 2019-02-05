package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.entity.sto.TechnicalService;
import com.smartcarservice.ua.SmartCarService.repository.DealerRepository;
import com.smartcarservice.ua.SmartCarService.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by 1 on 05.02.2019.
 */
@Service
public class DealerServiceImpl implements DealerService {

    @Autowired
    DealerRepository dealerRepo;

    @Override
    public void CreateDealer(Dealer dealer) {
        dealerRepo.save(dealer);
    }

    @Override
    public void DeleteDealer(Dealer dealer) {
        dealerRepo.delete(dealer);
    }

    @Override
    public void DeleteDealerById(Long id) {
        dealerRepo.deleteById(id);
    }

    @Override
    public void UpdateDealer(Dealer dealer) {
        dealerRepo.save(dealer);
    }

//    @Override
//    public Dealer findDealerByLogin(String login) {
//        return dealerRepo.find;
//    }


    @Override
    public Dealer findById(Long id) {
        return dealerRepo.getDealerById(id);
    }

    @Override
    public Dealer findDealerBySto(TechnicalService technicalService) {
        return dealerRepo.findDealerByTechnicalServices(technicalService);
    }

    @Override
    public List<Dealer> findAll() {
        return  dealerRepo.findAll();
    }

    @Override
    public Dealer findDealerBySaleManager(SalesManager salesManager) {
        return dealerRepo.findBySalesManagers(salesManager);
    }

    @Override
    public Dealer findDealerByCar(Car car) {
        return dealerRepo.findDealerByCars(car);
    }
}
