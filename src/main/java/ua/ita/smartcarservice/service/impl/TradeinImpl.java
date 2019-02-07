package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.repository.DealerRepository;
import ua.ita.smartcarservice.repository.TradeInRepository;
import ua.ita.smartcarservice.service.TradeInService;

import java.util.List;

/**
 * Created by 1 on 07.02.2019.
 */
@Service
public class TradeinImpl implements TradeInService {


    @Autowired
    TradeInRepository tradeInRepository;

    @Autowired
    DealerRepository dealerRepository;

    @Override
    public void createTradeIn(TradeIn tradeIn) {
        tradeInRepository.save(tradeIn);
    }

    @Override
    public void deleteTradeIn(TradeIn tradeIn) {
tradeInRepository.delete(tradeIn);
    }

    @Override
    public List<TradeIn> findAll() {
        return tradeInRepository.findAll();
    }

    @Override
    public List<TradeIn> findAllInSM(SalesManager salesManager) {
        return tradeInRepository.findAllBySalesManager(salesManager);
    }
}
