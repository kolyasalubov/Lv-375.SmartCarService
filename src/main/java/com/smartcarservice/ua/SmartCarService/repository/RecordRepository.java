package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sensors.data.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface RecordRepository<T extends RecordEntity> extends JpaRepository<T, Long> {

    @Query("SELECT t FROM #{#entityName} t WHERE MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)")
    public List<T> getByDay(@Param("date") Date date);

    @Query("SELECT t FROM #{#entityName} t WHERE MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)")
    public List<T> getByMonth(@Param("date") Date date);
}

