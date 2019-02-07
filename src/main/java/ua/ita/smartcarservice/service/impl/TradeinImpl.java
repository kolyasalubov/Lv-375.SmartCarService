package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.saleDto.TradeinDto;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.DealerRepository;
import ua.ita.smartcarservice.repository.SaleManagerRepository;
import ua.ita.smartcarservice.repository.TradeInRepository;
import ua.ita.smartcarservice.service.CarOwnerService;
import ua.ita.smartcarservice.service.TradeInService;

import java.util.ArrayList;
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

    @Autowired
    CarRepository carRepository;

    @Autowired
    SaleManagerRepository saleManagerRepository;

    @Autowired
    CarOwnerService carOwnerService;

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

    @Override
    public List<TradeinDto> findAllDtoInSM(SalesManager salesManager) {
        List<TradeinDto>tradeinDtos=new ArrayList<>();

        List<TradeIn>tradeIns=tradeInRepository.findAllBySalesManager(salesManager);

        for (int i=0;i<tradeIns.size();i++){


            tradeinDtos.add(new TradeinDto(

                    carOwnerService.getCarOwnerById(tradeIns.get(i).getIdUser()),
                    tradeIns.get(i).getSalesManager(),
                    carRepository.findByVin(tradeIns.get(i).getVinUserCar()),
                    carRepository.findByVin(tradeIns.get(i).getVinNewCar()) ));
        }


        return tradeinDtos;
    }
}
