package ua.ita.smartcarservice.service;


import ua.ita.smartcarservice.dto.CarDto;

import java.util.List;

public interface CarService {

    List<CarDto> findByUserId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);


}
