package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.TradeIn;
import ua.ita.smartcarservice.entity.sales.UserDealer;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;
import ua.ita.smartcarservice.repository.Sales.*;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.sales.TradeInService;

import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
@Service
public class TradeInServiceImpl implements TradeInService {

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DealerEntityRepository dealerEntityRepository;

    @Autowired
    UserDealerRepository userDealerRepository;

    @Autowired
    SaleManagerEntityRepository saleManagerEntityRepository;

    @Autowired
    UserSaleManagerRepository userSaleManagerRepository;

    @Autowired
    TradeInRepository tradeInRepository;



    @Override
    public void createTradeIn(TradeIn tradeIn) {
        tradeInRepository.save(tradeIn);
    }

    @Override
    public void deleteTradeIn(TradeIn tradeIn) {
tradeInRepository.delete(tradeIn);
    }

    @Override
    public void deleteTradeIn(Long id) {
tradeInRepository.deleteById(id);
    }

    @Override
    public void editTradeIn(TradeIn tradeIn) {
tradeInRepository.save(tradeIn);
    }

    @Override
    public List<TradeIn> findall() {
        return tradeInRepository.findAll();
    }

    @Override
    public List<TradeIn> findAllByDealerEntity(DealerEntity dealerEntity) {
        return tradeInRepository.findAllByDealerEntity(dealerEntity);
    }

    @Override
    public List<TradeIn> findAllByUserDealer(UserDealer userDealer) {
        return tradeInRepository.findAllByDealerEntity(dealerEntityRepository.getByUserDealers(userDealer));
    }

    @Override
    public List<TradeIn> findAllToSM(UserSaleManager userSaleManager) {

        return tradeInRepository.findAllByDealerEntity(dealerEntityRepository.getBySaleManagerEntities(saleManagerEntityRepository.getByUserSaleManagers(userSaleManager)));
    }
}
