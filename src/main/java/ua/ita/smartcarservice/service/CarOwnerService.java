package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.stoDto.CarOwnerDto;

public interface CarOwnerService {

    CarOwnerDto findByUserName(String userName);

    CarOwnerDto getCarOwnerById(Long id);
}
