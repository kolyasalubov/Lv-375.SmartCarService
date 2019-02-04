package com.smartcarservice.ua.SmartCarService.ServiceImpl;

import com.smartcarservice.ua.SmartCarService.Repository.SalesManagerRepository;
import com.smartcarservice.ua.SmartCarService.Service.SalesManagerService;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SalesManagerServiceImpl implements SalesManagerService {
    @Autowired
    SalesManagerRepository salesManagerRepository;

    @Override
    public List<SalesManagerEntity> findAll() {
        return salesManagerRepository.findAll();
    }

    @Override
    public void save(SalesManagerEntity salesManagerEntity) {
        salesManagerRepository.save(salesManagerEntity);
    }

    @Override
    public void customSave(String email, String fullname, String password, String username) {
        salesManagerRepository.customSave(email,fullname,password,username);
    }
}
