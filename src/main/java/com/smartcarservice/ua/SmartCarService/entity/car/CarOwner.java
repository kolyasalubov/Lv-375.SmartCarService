package com.smartcarservice.ua.SmartCarService.entity.car;

import com.smartcarservice.ua.SmartCarService.entity.UserBaseEntity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@Table(name = "carowners")
public class CarOwner extends UserBaseEntity {

	@OneToMany(mappedBy = "carOwner")
    private Set<Car> cars;
    
	
	//BaseUser constructor
    public CarOwner(String email, String password, String fullName, String userName) {
		super(email, password, fullName, userName);
		
	}
    
    
    public CarOwner(String email, String password, String fullName, String userName, Set<Car> cars) {
		super(email, password, fullName, userName);
		this.cars = cars;
	}


	//Getters & Setters
	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

    

}