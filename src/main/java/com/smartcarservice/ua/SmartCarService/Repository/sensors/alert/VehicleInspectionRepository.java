package com.smartcarservice.ua.SmartCarService.Repository.sensors.alert;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartcarservice.ua.SmartCarService.entity.sensors.alert.VehicleInspection;

@Repository
public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Long> {

	@Query ("select v from VehicleInspection v inner join v.car c "
			+ "where c.id = :carId "
			+ "order by v.dateOfInspection desc")
	List<VehicleInspection> getLastVehicleInspection (@Param ("carId") long carId);
}
