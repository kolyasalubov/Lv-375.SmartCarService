package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;

import java.util.List;

//@Repository
public interface SalesManagerService {

    public List<SalesManager> findAll();

    void save(SalesManagerDto salesManagerDto);

    void customSave(String email, String fullname, String password, String username);

    SalesManagerDto getSalesManagerDto(String username);

}


