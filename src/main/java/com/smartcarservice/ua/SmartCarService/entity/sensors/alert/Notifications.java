package com.smartcarservice.ua.SmartCarService.entity.sensors.alert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.car.CarOwner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@Entity
@Table (name = "notification_entity")
public class Notifications {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (length = 1050, name = "description", nullable = false)
	private String description;
	
	@Column (length = 1050, name = "icon_type", nullable = false)
	private String iconType;
	
	@OneToOne
	@JoinColumn(name = "car_id")
	Car car;
	
}
