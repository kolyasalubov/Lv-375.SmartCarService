package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;

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
