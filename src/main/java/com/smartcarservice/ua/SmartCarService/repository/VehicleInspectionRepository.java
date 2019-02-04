package com.smartcarservice.ua.SmartCarService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

@Repository
public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Long> {

	@Query ("select v from VehicleInspection v inner join v.car c "
			+ "where c.id = :carId and v.dateOfInspection "
			+ "in (select max(dateOfInspection) from VehicleInspection)")
	VehicleInspection getLastVehicleInspections (@Param ("carId") long carId);
	
	@Query (value = "select * from vehicle_inspection v \r\n" + 
			"where v.date_of_inspection \r\n" + 
			"in (select max(date_of_inspection) from vehicle_inspection \r\n" + 
			"where DATE(date_of_inspection) <= date_sub(NOW(), INTERVAL 357 DAY)\r\n" + 
			"group by car_id\r\n" + 
			")", nativeQuery = true)
	List<VehicleInspection> getCarsForYearlyInspection ();
}
