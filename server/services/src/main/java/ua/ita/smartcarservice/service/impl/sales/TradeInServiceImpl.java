package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.sales.CreateTraidInDto;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.sales.DealerEntityRepository;
import ua.ita.smartcarservice.repository.sales.TradeInRepository;
import ua.ita.smartcarservice.service.sales.TradeInService;

/**
 * Created by 1 on 27.02.2019.
 */
@Service
public class TradeInServiceImpl implements TradeInService {

    @Autowired
    private DealerEntityRepository dealerRepository;
@Autowired
private CarRepository carRepository;

    @Autowired
    private TradeInRepository tradeInRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createTradeIn(CreateTraidInDto traidInDto) {

        tradeInRepository.save(new TradeIn(traidInDto.getVinNewCar(),
                traidInDto.getVinUsedCar(),
                userRepository.findByUsername(traidInDto.getUsername()).get().getId(),
                dealerRepository.findByDealerEdr(traidInDto.getDealerEdr()).getDealerId(),
                "active"
               ));

    }

    @Override
    public void create(String vinNewCar, String vinUsedCar) {

        tradeInRepository.save(new TradeIn(vinNewCar,vinUsedCar,carRepository.findByVin(vinUsedCar).getUser().getId(),carRepository.findByVin(vinNewCar).getDealer().getDealerId(),"active"));

    }
}

