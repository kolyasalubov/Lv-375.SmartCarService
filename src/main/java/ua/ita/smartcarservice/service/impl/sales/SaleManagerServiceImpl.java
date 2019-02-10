package ua.ita.smartcarservice.service.impl.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sales.SaleManagerEntity;
import ua.ita.smartcarservice.entity.sales.UserDealer;
import ua.ita.smartcarservice.entity.sales.UserSaleManager;
import ua.ita.smartcarservice.repository.Sales.*;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.service.sales.SaleManagerService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 10.02.2019.
 */
@Service
public class SaleManagerServiceImpl implements SaleManagerService {

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
    public void createSaleManager(DealerEntity dealerEntity, Long id) {

        UserSaleManager userSaleManager=userSaleManagerRepository.getOne(id);
        List<UserSaleManager>userSaleManagers=new ArrayList<>();
        userSaleManagers.add(userSaleManager);

saleManagerEntityRepository.save(new SaleManagerEntity(userSaleManagers,dealerEntity));
    }

    @Override
    public void createSaleManager(DealerEntity dealerEntity) {
        saleManagerEntityRepository.save(new SaleManagerEntity(dealerEntity));
    }



    @Override
    public List<SaleManagerEntity> findByUserDealer(UserDealer userDealer) {
        return saleManagerEntityRepository.getAllByDealerEntity(dealerEntityRepository.getByUserDealers(userDealer));
    }

    @Override
    public List<SaleManagerEntity> findByUserDealer(DealerEntity dealerEntity) {
        return saleManagerEntityRepository.getAllByDealerEntity(dealerEntity);
    }

    @Override
    public void deleteSalemanager(Long id) {
      saleManagerEntityRepository.deleteById(id);
    }

    @Override
    public void editUserSaleManager(UserSaleManager userSaleManager) {
userSaleManagerRepository.save(userSaleManager);
    }

    @Override
    public void editSalemanagerEntity(SaleManagerEntity saleManagerEntity) {
saleManagerEntityRepository.save(saleManagerEntity);
    }

    @Override
    public UserSaleManager findbyId(Long id) {
        return userSaleManagerRepository.getOne(id);
    }

    @Override
    public SaleManagerEntity findById(Long id) {
        return saleManagerEntityRepository.getOne(id);
    }

    @Override
    public UserSaleManager findBySaleManagerEntity(SaleManagerEntity saleManagerEntity) {
        return userSaleManagerRepository.findBySaleManagerEntity(saleManagerEntity);
    }

    @Override
    public SaleManagerEntity findByUserSaleManager(UserSaleManager userSaleManager) {
        return saleManagerEntityRepository.getByUserSaleManagers(userSaleManager);
    }
}
