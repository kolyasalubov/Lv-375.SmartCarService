package ua.ita.smartcarservice.entity.sensors.data;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import ua.ita.smartcarservice.entity.Car;

@Entity
@Table(name = "fact_speed")
public class SpeedEntity extends SensorEntity implements ISensorEntity {

	@Override
	public void setDate(LocalDateTime date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Car getCar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCar(Car car) {
		// TODO Auto-generated method stub
		
	}

}
