package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.CarDto;
import java.util.List;

public interface CarService {

    List<CarDto> findByUserId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);

    void create(String brand, String model,String graduation_year,String number,String vin, String username);

    List<CarDto> findAll ();

    void deleteById (Long id);

    CarDto findByNumber (String number);



}
