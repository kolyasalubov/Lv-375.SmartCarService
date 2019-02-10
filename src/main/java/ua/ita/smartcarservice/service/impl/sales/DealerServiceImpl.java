package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.sales.*;
import ua.ita.smartcarservice.repository.Sales.*;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.sales.DealerService;

/**
 * Created by 1 on 10.02.2019.
 */
@Service
public class DealerServiceImpl implements DealerService {

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
    public void createDealerEntity(String name,String address, String edr, Long Id) {

        UserEntity userEntity=userRepository.getOne(Id);


        DealerEntity dealerEntity=new DealerEntity();
        dealerEntity.setName(name);
        dealerEntity.setAddress(address);
        dealerEntity.setEdr(edr);

        dealerEntityRepository.save(dealerEntity);

        userDealerRepository.save(new UserDealer(userEntity,dealerEntity));

    }

    @Override
    public DealerEntity getBySaleManager(UserSaleManager userSaleManager) {
        return dealerEntityRepository.getBySaleManagerEntities(saleManagerEntityRepository.getByUserSaleManagers(userSaleManager));
    }



    @Override
    public DealerEntity getByUserDealer(UserDealer userDealer) {
        return dealerEntityRepository.getByUserDealers(userDealer);
    }

    @Override
    public DealerEntity getByUserDealerId(Long UserDealerId) {
        return dealerEntityRepository.getByUserDealers(userDealerRepository.getById(UserDealerId));
    }


    @Override
    public DealerEntity getByTechnicalServiceEntityId(Long id) {
        return dealerEntityRepository.getByTechnicalServiceEntities(technicalServiceRepository.getByTechnicalServiceId(id));
    }

//    @Override
//    public DealerEntity getByUserTechnicalService(Long id) {
//        return dealerEntityRepository.getByTechnicalServiceEntities();
//    }

    @Override
    public DealerEntity getBySaleManagerEntity(SaleManagerEntity saleManagerEntity) {
        return dealerEntityRepository.getBySaleManagerEntities(saleManagerEntity);
    }

    @Override
    public DealerEntity getByUserSaleManager(UserSaleManager userSaleManager) {
        return dealerEntityRepository.getBySaleManagerEntities(saleManagerEntityRepository.getByUserSaleManagers(userSaleManager));
    }

    @Override
    public DealerEntity getByTradeIn(TradeIn tradeIn) {
        return dealerEntityRepository.getByTradeIns(tradeIn);
    }

    @Override
    public void editDealerEntity(DealerEntity dealerEntity) {
dealerEntityRepository.save(dealerEntity);
    }

    @Override
    public void editUserDealer(UserDealer userDealer) {
userDealerRepository.save(userDealer);
    }

}
