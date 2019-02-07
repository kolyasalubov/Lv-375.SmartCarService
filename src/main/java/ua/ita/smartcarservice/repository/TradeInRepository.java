package ua.ita.smartcarservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ita.smartcarservice.entity.sales.Dealer;
import ua.ita.smartcarservice.entity.sales.SalesManager;
import ua.ita.smartcarservice.entity.sales.TradeIn;

import java.util.List;

/**
 * Created by 1 on 07.02.2019.
 */
public interface TradeInRepository extends JpaRepository<TradeIn,Long> {



    List<TradeIn> findAllBySalesManager(SalesManager salesManager);
}
