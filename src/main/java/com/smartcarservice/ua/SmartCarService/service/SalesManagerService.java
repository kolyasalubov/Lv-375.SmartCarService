package com.smartcarservice.ua.SmartCarService.service;


import com.smartcarservice.ua.SmartCarService.dto.sales.SalesManagerDto;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;

import java.util.List;

//@repository
public interface SalesManagerService {

    List<SalesManager> findAll();

    void save(SalesManagerDto salesManagerDto);

    void customSave(String email, String fullname, String password, String username);

    SalesManagerDto getSalesManagerDto(String username);

    List<SalesManager> getAllSalesManagers();

    public SalesManagerDto entityToDto(SalesManager salesManager);

    public List<SalesManager> getAllSalesManagersByDealerUsername();

    SalesManager getSalesManagerById(Long id);

    SalesManagerDto getSalesManagerDtoById(Long id);

    List<SalesManagerDto> getAllSalesManagersByDealerUsername(String username);

    List<SalesManagerDto> salesManagerEntityListToDto(List<SalesManager> salesManagers);



}


