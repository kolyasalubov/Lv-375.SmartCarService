package com.smartcarservice.ua.SmartCarService.repository.sensors;

import com.smartcarservice.ua.SmartCarService.entity.sensors.data.BaseSensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorRepository<T extends BaseSensorEntity> extends JpaRepository<T, Long> {

    /* READ */

//    @Query("SELECT t FROM #{#entityName} t WHERE t.car.id = :carId " +
//            "AND DAY(t.date) = DAY(:date) AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)")
//    public List<T> getAllByDay(@Param("date") LocalDateTime date,
//                                @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, AVG(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<T> getAvgByMonth(@Param("date") LocalDateTime date,
//                                @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, MAX(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<T> getMaxByMonth(@Param("date") LocalDateTime date,
//                                 @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, MIN(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY DAY(t.date)")
//    public List<T> getMinByMonth(@Param("date") LocalDateTime date,
//                                 @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, AVG(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<T> getAvgByYear(@Param("date") LocalDateTime date,
//                                @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, MAX(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<T> getMaxByYear(@Param("date") LocalDateTime date,
//                                @Param("carId") long carId);
//
//    @Query("SELECT t.id, t.car, t.date, MIN(t.value) " +
//            "FROM #{#entityName} t " +
//            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
//            "GROUP BY MONTH(t.date)")
//    public List<T> getMinByYear(@Param("date") LocalDateTime date,
//                                @Param("carId") long carId);

}

