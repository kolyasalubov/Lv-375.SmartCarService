package com.smartcarservice.ua.SmartCarService.repository;

import com.smartcarservice.ua.SmartCarService.entity.sales.Dealer;
import com.smartcarservice.ua.SmartCarService.entity.sales.SalesManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by 1 on 05.02.2019.
 */
public interface SaleManagerRepository extends JpaRepository<SalesManager,Long> {

//    @Query("select s from SalesManager s where d.id=:id")
//    SalesManager getsalemanagerById(@Param("id") Long id);
//
    SalesManager getSalesManagerById(Long id);

    Set<SalesManager> findAllByDealer(Dealer dealer);
}
