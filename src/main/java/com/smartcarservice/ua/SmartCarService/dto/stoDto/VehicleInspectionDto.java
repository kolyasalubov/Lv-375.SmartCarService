package com.smartcarservice.ua.SmartCarService.dto.stoDto;

import java.sql.Date;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;

import lombok.Data;

@Data
public class VehicleInspectionDto {
	
	private long id;
	private Date dateOfInspection;
	private Integer mileageOfCar;
	private Car car;
	
	public VehicleInspectionDto(long id, Date dateOfInspection, Integer mileageOfCar, Car car) {
		this.id = id;
		this.dateOfInspection = dateOfInspection;
		this.mileageOfCar = mileageOfCar;
		this.car = car;
	}
	
}
