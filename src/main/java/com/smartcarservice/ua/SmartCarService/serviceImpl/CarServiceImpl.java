package com.smartcarservice.ua.SmartCarService.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.repository.CarRepository;
import com.smartcarservice.ua.SmartCarService.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;

	
	public Car create(Car car) {
		return carRepository.save(car);
	}

	public List<Car> findAll() {
		return carRepository.findAll();
	}

	public List<Car> findByCarOwnerId(Long id) {
		return carRepository.findByCarOwnerId(id);
	}

	public Car findById(Long id) {
		return carRepository.getOne(id);
	}

	public void deleteById(Long id) {
		carRepository.deleteById(id);
	}

	public Car findByVin (String vin) {
		return carRepository.findByVin(vin);
	}

}
