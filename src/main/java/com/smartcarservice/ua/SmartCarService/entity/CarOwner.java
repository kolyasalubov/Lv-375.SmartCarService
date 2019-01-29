package com.smartcarservice.ua.SmartCarService.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name = "car_owners")
public class CarOwner extends UserBaseEntity {

	@Column(nullable = true, unique = false)
    @Getter
    @Setter
	List<Car> cars;

}
