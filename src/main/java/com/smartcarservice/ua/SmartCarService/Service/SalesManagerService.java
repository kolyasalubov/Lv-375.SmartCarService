package com.smartcarservice.ua.SmartCarService.Service;


import com.smartcarservice.ua.SmartCarService.Repository.SalesManagerRepository;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManagerEntity;

import java.util.List;

public interface SalesManagerService {

    public List<SalesManagerEntity> findAll();

    void save(SalesManagerEntity salesManagerEntity);

    void customSave(String email, String fullname, String password, String username);


}


