package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.repository.Sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.Sales.TradeInRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.sales.TradeInService;

/**
 * Created by 1 on 17.02.2019.
 */
@Service

public class TradeInServiceImpl implements TradeInService {

    @Autowired
    private DealerEntityRepository dealerRepository;


    @Autowired
    private TradeInRepository tradeInRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createTradeIn(CreateTraidInDto traidInDto) {

//        tradeInRepository.save(new TradeIn(traidInDto.getVinNewCar(),traidInDto.getVinUsedCar(),userRepository.findByUsername()));

    }
}
