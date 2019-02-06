package com.smartcarservice.ua.SmartCarService.serviceImpl;

import com.smartcarservice.ua.SmartCarService.Repository.DealerRepository;
import com.smartcarservice.ua.SmartCarService.Repository.SalesManagerRepository;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import com.smartcarservice.ua.SmartCarService.service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                salesManagerDto.getFullName(),
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
        SalesManager salesManagerEntity = salesManagerRepository.findByUserName(username);
        SalesManagerDto salesManagerDto = new SalesManagerDto(
                salesManagerEntity.getEmail(),
                salesManagerEntity.getPassword(),
                salesManagerEntity.getFullName(),
                salesManagerEntity.getUserName(),
                salesManagerEntity.getDealer().getUserName()
        );
        return salesManagerDto;
    }
}
