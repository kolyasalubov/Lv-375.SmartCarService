package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.repository.DealerRepository;
import ua.ita.smartcarservice.repository.SaleManagerRepository;
import ua.ita.smartcarservice.service.SalesManagerService;

import java.util.Set;

/**
 * Created by 1 on 05.02.2019.
 */
@Service
public class SalesManagerImpl implements SalesManagerService {

    @Autowired
    private SaleManagerRepository saleManagerRepository;

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public void createSaleManager(SalesManager salesManager) {
        saleManagerRepository.save(salesManager);
    }

    @Override
    public void deleteSalesManager(SalesManager salesManager) {
saleManagerRepository.delete(salesManager);
    }

    @Override
    public void updateSalesManager(SalesManager salesManager) {
   saleManagerRepository.save(salesManager);
    }

    @Override
    public void deleteSalesManagerbyId(Long id) {
     saleManagerRepository.delete(saleManagerRepository.getSalesManagerById(id));
    }

    @Override
    public Set<SalesManager> findAll() {
        return (Set<SalesManager>) saleManagerRepository.findAll();
    }

    @Override
    public Set<SalesManager> findAllByDealer(Dealer dealer) {
        return saleManagerRepository.findAllByDealer(dealer);
    }

    @Override
    public Set<SalesManager> findAllByDealerId(Long id) {
        return saleManagerRepository.findAllByDealer(dealerRepository.getDealerById(id));
    }

}
