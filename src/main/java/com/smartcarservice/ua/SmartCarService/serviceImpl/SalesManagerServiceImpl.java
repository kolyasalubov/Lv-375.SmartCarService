package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.repository.DealerRepository;
import com.smartcarservice.ua.SmartCarService.repository.SalesManagerRepository;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Builder
@Service
public class SalesManagerServiceImpl implements SalesManagerService {
    @Autowired
    SalesManagerRepository salesManagerRepository;
    @Autowired
    DealerRepository dealerRepository;

    @Override
    public List<SalesManager> findAll() {
        return salesManagerRepository.findAll();
    }

    @Override
    public void save(SalesManagerDto salesManagerDto) {
        SalesManager entity = new SalesManager(
                salesManagerDto.getEmail(),
                salesManagerDto.getPassword(),
                salesManagerDto.getFullName(),
                salesManagerDto.getUserName(),
                dealerRepository.getDealerByUserName(salesManagerDto.getDealerUsername()));
        salesManagerRepository.save(entity);
    }

    @Override
    public void customSave(String email, String fullname, String password, String username) {
//        salesManagerRepository.customSave(email,fullname,password,username);
    }

    @Override
    public SalesManagerDto getSalesManagerDto(String username) {
//        salesManagerRepository.findAll().stream().
//        SalesManager salesManagerEntity = salesManagerRepository.findByUserName(username).get(0);
        SalesManager salesManagerEntity = salesManagerRepository.getByUserName(username);
        SalesManagerDto salesManagerDto = new SalesManagerDto(
                salesManagerEntity.getEmail(),
                salesManagerEntity.getPassword(),
                salesManagerEntity.getFullName(),
                salesManagerEntity.getUserName(),
                salesManagerEntity.getDealer().getUserName()
        );
        return salesManagerDto;
    }

    public void updateSalesManagerByUsername(String username, SalesManagerDto salesManagerDto) {
        SalesManager salesManager = salesManagerRepository.getByUserName(username);
        salesManager.setEmail(salesManagerDto.getEmail());
        salesManager.setPassword(salesManagerDto.getPassword());
        salesManager.setFullName(salesManagerDto.getFullName());
        salesManager.setUserName(salesManagerDto.getUserName());
        dealerRepository.getDealerByUserName(salesManagerDto.getDealerUsername());
        salesManagerRepository.save(salesManager);

    }

    public List<SalesManager> getAllSalesManagers() {
        return (List<SalesManager>) salesManagerRepository.findAll();
    }

    @Override
    public SalesManager getSalesManagerById(Long id) {
        return salesManagerRepository.getSalesManagerById(id);
    }

    @Override
    public SalesManagerDto getSalesManagerDtoById(Long id) {
        return null;
    }

    @Override
    public  List<SalesManagerDto> salesManagerEntityListToDto(List<SalesManager> salesManagers){
        List<SalesManagerDto> salesManagerDtos=new ArrayList<>();
        for (SalesManager salesManager:salesManagers){
            salesManagerDtos.add(entityToDto(salesManager));
        }
        return salesManagerDtos;
//
    }
    @Override
    public List<SalesManagerDto> getAllSalesManagersByDealerUsername(String username) {
        return salesManagerEntityListToDto(salesManagerRepository.getAllByDealer_UserName()); //entityToDto(salesManagerRepository.getAllByDealer_UserName());
    }

    public SalesManagerDto entityToDto(SalesManager salesManager) {
        return new SalesManagerDto(
                salesManager.getEmail(),
                salesManager.getPassword(),
                salesManager.getFullName(),
                salesManager.getUserName(),
                salesManager.getDealer().getUserName()
        );
    }

    @Override
    public List<SalesManager> getAllSalesManagersByDealerUsername() {
        return salesManagerRepository.getAllByDealer_UserName();
    }

}
