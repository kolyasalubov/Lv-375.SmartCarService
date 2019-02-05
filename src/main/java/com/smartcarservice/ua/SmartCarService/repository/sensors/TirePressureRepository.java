package com.smartcarservice.ua.SmartCarService.repository.sensors;


import com.smartcarservice.ua.SmartCarService.entity.sensors.data.TirePressureEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TirePressureRepository extends SensorRepository<TirePressureEntity> {

//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "AVG(t.valueFrontLeft), AVG(t.valueFrontRight), AVG(t.valueBackLeft), AVG(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<TirePressureEntity> getAvgByMonth(@Param("date") LocalDateTime date,
//                                                  @Param("carId") long carId);
//
//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "MAX(t.valueFrontLeft), MAX(t.valueFrontRight), MAX(t.valueBackLeft), MAX(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<TirePressureEntity> getMaxByMonth(@Param("date") LocalDateTime date,
//                                                  @Param("carId") long carId);
//
//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "MIN(t.valueFrontLeft), MIN(t.valueFrontRight), MIN(t.valueBackLeft), MIN(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<TirePressureEntity> getMinByMonth(@Param("date") LocalDateTime date,
//                                                  @Param("carId") long carId);
//
//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "AVG(t.valueFrontLeft), AVG(t.valueFrontRight), AVG(t.valueBackLeft), AVG(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<TirePressureEntity> getAvgByYear(@Param("date") LocalDateTime date,
//                                                 @Param("carId") long carId);
//
//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "MAX(t.valueFrontLeft), MAX(t.valueFrontRight), MAX(t.valueBackLeft), MAX(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<TirePressureEntity> getMaxByYear(@Param("date") LocalDateTime date,
//                                                 @Param("carId") long carId);
//
//    @Override
//    @Query("SELECT t.id, t.car, t.date, " +
//            "MIN(t.valueFrontLeft), MIN(t.valueFrontRight), MIN(t.valueBackLeft), MIN(t.valueBackRight) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<TirePressureEntity> getMinByYear(@Param("date") LocalDateTime date,
//                                                 @Param("carId") long carId);
}
