package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sales.TradeIn;

import java.util.List;

/**
 * Created by 1 on 07.02.2019.
 */
public interface TradeInService {

    void createTradeIn(TradeIn tradeIn);

    void deleteTradeIn(TradeIn tradeIn);

    List<TradeIn>findAll();

    List<TradeIn>findAllInSM(SalesManager salesManager);





}
