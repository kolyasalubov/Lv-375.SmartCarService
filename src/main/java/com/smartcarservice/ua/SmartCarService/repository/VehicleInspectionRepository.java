package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcarservice.ua.SmartCarService.entity.car.Car;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

@Repository
public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Long> {

	@Query ("select v from VehicleInspection v inner join v.car c "
			+ "where c.id = :carId and v.dateOfInspection "
			+ "in (select max(dateOfInspection) from VehicleInspection)")
	VehicleInspection getLastVehicleInspections (@Param ("carId") long carId);
	
	//  select all where: lastInspectionDate away from todays date unless on 365 days and
	//  car has not have an appointment on vehicle inspection
	//  skill_id = 7 is vehicle inspectors id 
	@Query (value = "select * from vehicle_inspection v \r\n" + 
					"where v.date_of_inspection in\r\n" + 
					"	(select max(date_of_inspection) from vehicle_inspection \r\n" + 
					"	where DATE(date_of_inspection) <= date_sub(NOW(), INTERVAL 365 DAY)\r\n" + 
					"	group by car_id)\r\n" + 
					"AND v.car_id not in \r\n" + 
					"	(select id from session s\r\n" + 
					"	inner join worker w on w.worker_id = s.worker_id \r\n" + 
					"	where skill_id = 7);", 
			nativeQuery = true)
	List<VehicleInspection> getCarsForYearlyInspection ();
	
	@Query(value = "select * from car c\r\n" + 
			"inner join vehicle_inspection v on v.car_id = c.id\r\n" + 
			"inner join fact_mileage f on f.car_id = c.id\r\n" + 
			"where v.date_of_inspection in\r\n" + 
			"	(select max(date_of_inspection) from vehicle_inspection \r\n" + 
			"	group by car_id)    \r\n" + 
			"and f.id in \r\n" + 
			"	(select max(id) from fact_mileage)\r\n" + 
			"and\r\n" + 
			"(f.value - v.mileage_of_car) >= 10",
			nativeQuery =  true)
	List<Car> getCarsForInspectionByMileage();
}
