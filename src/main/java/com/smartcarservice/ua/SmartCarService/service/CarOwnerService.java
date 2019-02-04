package com.smartcarservice.ua.SmartCarService.service;

import com.smartcarservice.ua.SmartCarService.dto.stoDto.CarOwnerDto;

public interface CarOwnerService {

    CarOwnerDto findByUserName(String userName);

    CarOwnerDto getCarOwnerById(Long id);
}
