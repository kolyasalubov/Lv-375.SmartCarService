package com.smartcarservice.ua.SmartCarService.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;
import com.smartcarservice.ua.SmartCarService.repository.CarOwnerRepository;
import com.smartcarservice.ua.SmartCarService.service.CarOwnerService;

@Service
public class CarOwnerServiceImpl implements CarOwnerService {

	@Autowired
	private CarOwnerRepository carOwnerRepository;
	
	public CarOwner create (CarOwner carOwner) {
		return carOwnerRepository.save(carOwner);
	}
	
	public CarOwner findById (Long id) {
		return carOwnerRepository.getOne(id);
	}
	
	public List <CarOwner> findAll () {
		return carOwnerRepository.findAll();
	}
	
	public CarOwner findByUserName (String userName) {
		return carOwnerRepository.findByUserName(userName);
	}
	
	public void deleteById (Long id) {
		carOwnerRepository.deleteById(id);
	}
	
	public CarOwner resetPassword (CarOwner carOwner) {
		CarOwner carOwnerToUpdate = carOwnerRepository.getOne(carOwner.getId());
		carOwnerToUpdate.setPassword(carOwner.getPassword());
		return carOwnerRepository.save(carOwnerToUpdate);
	}
	
}
